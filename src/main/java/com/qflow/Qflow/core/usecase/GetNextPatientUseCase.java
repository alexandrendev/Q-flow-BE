package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.ports.TriageQueueRepository;

public class GetNextPatientUseCase {
    private final TriageQueueRepository repository;


    public Long execute(Long tenantId) {
        return this.repository.pickNextAndMarkInProgress(tenantId);
    }

    public GetNextPatientUseCase(TriageQueueRepository repository) {
        this.repository = repository;
    }
}
