package com.qflow.Qflow.infra.repository.triage_queue;

import com.qflow.Qflow.core.ports.TriageQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public void changeStatusToInProgress(Long patientId) {
        String sql = "UPDATE triage_queue SET status = 'IN_PROGRESS' WHERE patient_id = :patientId AND status <> 'FINISHED'";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("patientId", patientId);
        jdbcTemplate.update(sql, params);
    }

    @Transactional
    @Override
    public Long pickNextAndMarkInProgress(Long tenantId) {
        String selectSql = ""
                + "SELECT id, patient_id "
                + "FROM triage_queue "
                + "WHERE status = 'WAITING' AND tenant_id = :tenantId "
                + "ORDER BY created_at ASC "
                + "LIMIT 1 "
                + "FOR UPDATE SKIP LOCKED";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tenantId", tenantId);

        return jdbcTemplate.query(selectSql, params, rs -> {
            if (!rs.next()) {
                return null;
            }

            Long rowId = rs.getLong("id");
            Long patientId = rs.getLong("patient_id");

            String updateSql = "UPDATE triage_queue SET status = 'IN_PROGRESS', updated_at = now() WHERE id = :id AND status = 'WAITING'";
            MapSqlParameterSource updateParams = new MapSqlParameterSource();
            updateParams.addValue("id", rowId);

            int updated = jdbcTemplate.update(updateSql, updateParams);
            if (updated == 0) {
                // alguém consumiu antes do update; retornar null para indicar falha
                return null;
            }

            return patientId;
        });
    }


    @Autowired
    public TriageQueueRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
