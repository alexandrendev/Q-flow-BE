package com.qflow.Qflow.infra.repository.tenant;

import com.qflow.Qflow.core.entity.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TenantRepository {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<Tenant> save(Tenant tenant) {
        String sql = "INSERT INTO tenants (name, cnpj, city) " +
                "VALUES (:name, :cnpj, :city) RETURNING id;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", tenant.getName());
        params.addValue("cnpj", tenant.getCNPJ());
        params.addValue("city", tenant.getCity());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, params, keyHolder);

        tenant.setId(keyHolder.getKey().longValue());

        return tenant.getId() != null ? Optional.of(tenant) : Optional.empty();
    }

    @Autowired
    public  TenantRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
