package com.qflow.Qflow.core.entity.user;

public record RegisterDTO(
        Long tenantId,
        String name,
        String email,
        String password,
        Role role
) {
}
