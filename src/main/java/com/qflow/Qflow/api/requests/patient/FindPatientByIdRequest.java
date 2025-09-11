package com.qflow.Qflow.api.requests.patient;

public record FindPatientByIdRequest(
        Long patientId,
        Long tenantId
) {
}
