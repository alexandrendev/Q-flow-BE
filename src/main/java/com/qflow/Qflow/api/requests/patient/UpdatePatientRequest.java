package com.qflow.Qflow.api.requests.patient;

public record UpdatePatientRequest(
        Long id,
        String name,
        Long tenantId
) {
}
