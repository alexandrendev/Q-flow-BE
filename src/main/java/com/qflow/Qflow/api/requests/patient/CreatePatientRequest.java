package com.qflow.Qflow.api.requests.patient;

public record CreatePatientRequest(
        String name, Long tenantId
) {}
