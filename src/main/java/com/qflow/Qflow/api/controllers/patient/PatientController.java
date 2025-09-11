package com.qflow.Qflow.api.controllers.patient;

import com.qflow.Qflow.api.requests.SetManschesterPriorityRequest;
import com.qflow.Qflow.core.entity.patient.Patient;
import com.qflow.Qflow.core.entity.user.User;
import com.qflow.Qflow.core.ports.PatientRepository;
import com.qflow.Qflow.api.requests.patient.CreatePatientRequest;
import com.qflow.Qflow.api.requests.patient.UpdatePatientRequest;
import com.qflow.Qflow.core.usecase.SetManchesterPriorityUseCase;
import com.qflow.Qflow.infra.security.MyUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @Operation(
            summary = "Creates a new Patient record",
            description = "Returns the created Patient object with assigned ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully created Patient",
                    content = @Content(
                            mediaType = "application/json"
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Patient data"),
    })
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

    @Operation(
            summary = "Get Patient by ID",
            description = "Returns the Patient object for the given ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved Patient",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)
                    )
            ),
            @ApiResponse(responseCode = "204", description = "Patient not found"),
    })
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> findById(@PathVariable Long patientId) {
        Patient patient = patientRepository.findById(patientId);
        if (patient != null) {
            return ResponseEntity.ok(patient);
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(
            summary = "Get all Patients by Tenant ID",
            description = "Returns a list of Patient objects for the given Tenant ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully retrieved Patients",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)
                    )
            ),
            @ApiResponse(responseCode = "204", description = "No Patients found for the given Tenant ID"),
    })
    @GetMapping("/all/{tenantId}")
    public ResponseEntity<List<Patient>> findAllByTenantId(@PathVariable Long tenantId) {
        var patients = patientRepository.findAllByTenantId(tenantId);

        if (!patients.isEmpty()) {
            return ResponseEntity.ok(patients);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update an existing Patient record",
            description = "Returns the updated Patient object"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully updated Patient",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Patient data"),
    })
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

    @Operation(
            summary = "Delete Patient by ID",
            description = "Deletes the Patient record for the given ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted Patient"),
            @ApiResponse(responseCode = "400", description = "Invalid Patient ID"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Set Manchester Priority for a Patient",
            description = "Sets the suggested Manchester Priority for the given Patient ID and returns the updated Patient object"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successfully updated Patient priority",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Patient.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid Patient ID or Priority"),
            @ApiResponse(responseCode = "204", description = "Patient not found"),
    })
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
