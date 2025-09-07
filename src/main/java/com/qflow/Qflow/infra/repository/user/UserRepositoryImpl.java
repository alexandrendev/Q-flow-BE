package com.qflow.Qflow.infra.repository.user;

import com.qflow.Qflow.core.entity.user.Role;
import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = :email";
        Map<String, Object> params = Map.of(
                "email", email
        );

        var users = jdbcTemplate.query(sql, params, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getLong("tenant_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.fromString(rs.getString("role")),
                        rs.getObject("created_at", java.time.LocalDateTime.class),
                        rs.getObject("updated_at", java.time.LocalDateTime.class)
                )
        );

        return users.isEmpty() ? null : users.get(0);

    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (tenant_id, name, email, password, role) " +
                "VALUES (:tenantId, :name, :email, :password, :role)";
        Map<String, Object> params = Map.of(
                "tenantId", user.getTenantId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "password", user.getPassword(),
                "role", user.getRole().getRoleName()
        );

        jdbcTemplate.update(sql, params);
    }

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
