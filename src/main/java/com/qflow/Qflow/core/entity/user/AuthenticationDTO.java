package com.qflow.Qflow.core.entity.user;

public record AuthenticationDTO(
    String email,
    String password
) {
}
