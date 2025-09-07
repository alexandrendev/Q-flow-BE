package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.ports.PatientRepository;

public class SetPatientSuggestedPriorityUseCase {
    private PatientRepository repository;

    public void execute(ManchesterPriority priority, Long patientId) {
        repository.setSuggestedPriority(priority, patientId);

    }

    public SetPatientSuggestedPriorityUseCase(PatientRepository repository) {
        this.repository = repository;
    }
}
