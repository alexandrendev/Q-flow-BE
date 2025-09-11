package com.qflow.Qflow.api.responses;

public record TriageQueueResponse(
        Long id,
        Long userId,
        Long patientId,
        Long tenantId,
        Status status
) {
}
