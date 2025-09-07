package com.qflow.Qflow.services;

import com.qflow.Qflow.core.entity.patient.Patient;
import com.qflow.Qflow.core.ports.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public Optional<Patient> save(Patient patient) {return patientRepository.save(patient);}

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
}
