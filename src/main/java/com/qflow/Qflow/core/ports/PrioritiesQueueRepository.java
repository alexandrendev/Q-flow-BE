package com.qflow.Qflow.core.ports;

public interface PrioritiesQueueRepository {

    Long addPatientToQueue(Long userId, Long patientId, Long tenantId);
}
