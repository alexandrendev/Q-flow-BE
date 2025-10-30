package com.qflow.Qflow.core.ports;

public interface TriageQueueRepository {

    Long addPatientToQueue(Long userId, Long patientId, Long tenantId);

    void changeStatusToFinished(Long patientId);
}
