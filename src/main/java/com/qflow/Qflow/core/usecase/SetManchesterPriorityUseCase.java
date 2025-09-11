package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.patient.Patient;
import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.PatientRepository;

public class SetManchesterPriorityUseCase {
    private PatientRepository repository;

    public Patient execute(User user, ManchesterPriority priority, Long patientId) {
        Patient existingPatient = repository.findById(patientId);

        if (existingPatient == null) throw new IllegalArgumentException("Patient not found");

        existingPatient.setAssignedPriority(priority);

        repository.setAssignedPriority(existingPatient);

        return existingPatient;
    }

    public SetManchesterPriorityUseCase(PatientRepository repository) {
        this.repository = repository;
    }
}
