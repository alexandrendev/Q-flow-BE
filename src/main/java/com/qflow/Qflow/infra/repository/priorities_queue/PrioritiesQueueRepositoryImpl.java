package com.qflow.Qflow.infra.repository.priorities_queue;

import com.qflow.Qflow.core.ports.PrioritiesQueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PrioritiesQueueRepositoryImpl implements PrioritiesQueueRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Long addPatientToQueue(Long userId, Long patientId, Long tenantId) {
        String sql = "INSERT INTO priorities_queue (user_id, patient_id, tenant_id) " +
                "VALUES (:userId, :patientId, :tenantId) RETURNING id;";


        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("userId", userId);
        params.addValue("patientId", patientId);
        params.addValue("tenantId", tenantId);


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder);


        return Long.valueOf(keyHolder.getKey().longValue());
    }

    @Autowired
    public PrioritiesQueueRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
