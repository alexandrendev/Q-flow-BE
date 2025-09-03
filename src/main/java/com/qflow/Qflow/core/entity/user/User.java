package com.qflow.Qflow.core.entity.user;


import java.time.LocalDateTime;

public class User {
    private Long id;
    private Long tenantId;
    private String name;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(Long tenantId, String name, String email, String password, Role role) {
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(Long id,
                Long tenantId,
                String name,
                String email,
                String password,
                Role role,
                LocalDateTime createdAt,
                LocalDateTime updatedAt
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
