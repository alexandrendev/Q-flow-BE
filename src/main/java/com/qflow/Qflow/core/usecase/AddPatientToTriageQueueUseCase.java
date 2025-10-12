package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.ports.TriageQueueRepository;

public class AddPatientToTriageQueueUseCase {
    private final TriageQueueRepository repository;

    public Long execute(Long userId, Long patientId, Long tenantId) {
        return repository.addPatientToQueue(userId, patientId, tenantId);
    }

    public AddPatientToTriageQueueUseCase(TriageQueueRepository repository) {
        this.repository = repository;
    }
}
