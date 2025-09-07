package com.qflow.Qflow.api.requests;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;

public record SetManschesterPriorityRequest(
        Long patientId,
        ManchesterPriority priority
) {
}
