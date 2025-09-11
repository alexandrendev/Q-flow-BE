package com.qflow.Qflow.core.ports;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.patient.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {

    Optional<Patient> save(Patient patient);

    Patient findById(Long id);

    void deleteById(Long id);

    Patient update(Patient patient);

    void setSuggestedPriority(ManchesterPriority priority, Long patientId);

    List<Patient> findAllByTenantId(Long tenantId);

    Patient setAssignedPriority(Patient patient);
}
