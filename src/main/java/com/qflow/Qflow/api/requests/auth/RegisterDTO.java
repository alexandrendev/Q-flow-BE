package com.qflow.Qflow.api.requests.auth;

import com.qflow.Qflow.core.entity.user.Role;

public record RegisterDTO(
        Long tenantId,
        String name,
        String email,
        String password,
        Role role
) {
}
