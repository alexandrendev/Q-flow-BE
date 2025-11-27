package com.qflow.Qflow.core.ports;

import com.qflow.Qflow.api.responses.TriageQueueResponse;
import java.util.List;

public interface TriageQueueRepository {

    Long addPatientToQueue(Long userId, Long patientId, Long tenantId);

    void changeStatusToFinished(Long patientId);
    void changeStatusToInProgress(Long patientId);
    Long pickNextAndMarkInProgress(Long tenantId);
    List<TriageQueueResponse> getAllWaitingPatients(Long tenantId);
}
