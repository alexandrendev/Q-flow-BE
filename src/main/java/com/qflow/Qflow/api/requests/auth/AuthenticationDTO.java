package com.qflow.Qflow.api.requests.auth;

public record AuthenticationDTO(
    String email,
    String password
) {
}
