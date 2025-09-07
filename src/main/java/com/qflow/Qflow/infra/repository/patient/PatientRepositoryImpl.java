package com.qflow.Qflow.infra.repository.patient;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.patient.Patient;
import com.qflow.Qflow.core.ports.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<Patient> save(Patient patient) {
        String sql = "INSERT INTO patients (name, tenant_id) " +
                "VALUES (:name, :tenantId) RETURNING id;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", patient.getName());
        params.addValue("tenantId", patient.getTenantId());


        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        patient.setId(keyHolder.getKey().longValue());

        return patient.getId() != null ? Optional.of(patient) : Optional.empty();
    }

    @Override
    public Patient findById(Long id) {
        String sql = "SELECT * FROM patient WHERE id = :id";

        var params = Map.of("id", id);

        var patient = jdbcTemplate.query(sql, params, (rs, rowNum) ->
                new Patient(
                        rs.getString("name")
                )
        );

        return patient.isEmpty() ? null : patient.get(0);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM patients WHERE id = :id";

        var params = Map.of("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public Patient update(Patient patient) {
        String sql = "UPDATE patients SET name = :name WHERE id = :id";
        var params = Map.of(
                "id", patient.getName(),
                "name", patient.getName()
        );

        int rows = jdbcTemplate.update(sql, params);

        return rows > 0
                ? patient
                : null;
    }

    @Override
    public void setSuggestedPriority(ManchesterPriority priority, Long patientId) {
        String sql = "UPDATE patients SET suggested_priority = :priority WHERE id = :id";
        var params = Map.of(
                "id", patientId,
                "priority", priority.name()
        );
        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Patient> findAllByTenantId(Long tenantId) {
        String sql = "SELECT * FROM patients WHERE tenant_id = :tenantId";
        var params = Map.of("tenantId", tenantId);

        return jdbcTemplate.query(sql, params, (rs, rowNum) -> {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id"));
            patient.setName(rs.getString("name"));
            patient.setTenantId(rs.getLong("tenant_id"));
            String priority = rs.getString("suggested_priority");
            if (priority != null) {
                patient.setSuggestedPriority(ManchesterPriority.valueOf(priority));
            }
            patient.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            patient.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return patient;
        });
    }

    @Autowired
    public PatientRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
