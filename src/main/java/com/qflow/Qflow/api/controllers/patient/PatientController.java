package com.qflow.Qflow.api.controllers.patient;

import com.qflow.Qflow.api.requests.SetManschesterPriorityRequest;
import com.qflow.Qflow.api.requests.patient.CreatePatientRequest;
import com.qflow.Qflow.api.requests.patient.UpdatePatientRequest;
import com.qflow.Qflow.core.entity.patient.Patient;
import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.PatientRepository;
import com.qflow.Qflow.core.usecase.SetManchesterPriorityUseCase;
import com.qflow.Qflow.infra.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private  final PatientRepository patientRepository;
    private final SetManchesterPriorityUseCase setManchesterPriorityUseCase;

    @PostMapping("/create")
    public ResponseEntity<Patient> save(@RequestBody CreatePatientRequest request) {
        Patient patient = new Patient();
        patient.setName(request.name());
        patient.setTenantId(request.tenantId());
        Optional<Patient> optional = patientRepository.save(patient);
        if(optional.isPresent()){
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> findById(@PathVariable Long patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/all/{tenantId}")
    public ResponseEntity<List<Patient>> findAllByTenantId(@PathVariable Long tenantId) {
        var patients = patientRepository.findAllByTenantId(tenantId);

        if (!patients.isEmpty()) {
            return ResponseEntity.ok(patients);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<Patient> update(@PathVariable Long patientId, @RequestBody UpdatePatientRequest request) {
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setName(request.name());
        patient.setTenantId(request.tenantId());
        Patient updatedPatient = patientRepository.update(patient);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/set-priority")
    public ResponseEntity<Patient> setManchesterPriority(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody SetManschesterPriorityRequest request
    ) {

        User user = userDetails.getUser();
        Patient patient = setManchesterPriorityUseCase.execute(user, request.priority(), request.patientId());

        if (patient != null) {
            return ResponseEntity.ok(patient);
        }

        return ResponseEntity.badRequest().build();
    }



    @Autowired
    public PatientController(PatientRepository patientRepository, SetManchesterPriorityUseCase setManchesterPriorityUseCase) {
        this.patientRepository = patientRepository;
        this.setManchesterPriorityUseCase = setManchesterPriorityUseCase;
    }
}
