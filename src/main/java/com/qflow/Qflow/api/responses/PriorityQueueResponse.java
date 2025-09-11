package com.qflow.Qflow.api.responses;

public record PriorityQueueResponse(
        Long id,
        Long userId,
        Long patientId,
        Long tenantId,
        Status status
) {
}
