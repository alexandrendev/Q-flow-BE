package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.PrioritiesQueueRepository;

public class AddPatientToPrioritiesQueue {
    private final PrioritiesQueueRepository priorityQueueRepository;

    public void execute(Long userId, Long patientId, Long tenantId) {
        priorityQueueRepository.addPatientToQueue(userId, patientId, tenantId);
    }

    public AddPatientToPrioritiesQueue(PrioritiesQueueRepository priorityQueueRepository) {
        this.priorityQueueRepository = priorityQueueRepository;
    }
}
