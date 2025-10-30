package com.qflow.Qflow.infra.repository.triage_queue;

import com.qflow.Qflow.core.ports.TriageQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TriageQueueRepositoryImpl implements TriageQueueRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Long addPatientToQueue(Long userId, Long patientId, Long tenantId) {
        String sql = "INSERT INTO triage_queue (user_id, patient_id, tenant_id, status) " +
                "VALUES (:userId, :patientId, :tenantId, 'WAITING') RETURNING id;";


        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", userId);
        params.addValue("patientId", patientId);
        params.addValue("tenantId", tenantId);


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);


        return Long.valueOf(keyHolder.getKey().longValue());
    }

    @Override
    public void changeStatusToFinished(Long patientId) {
        String sql = "UPDATE triage_queue SET status = 'FINISHED' WHERE patient_id = :patientId AND status <> 'FINISHED'";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("patientId", patientId);
        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public TriageQueueRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
