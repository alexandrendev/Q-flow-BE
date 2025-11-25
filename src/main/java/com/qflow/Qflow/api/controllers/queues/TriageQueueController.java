package com.qflow.Qflow.api.controllers.queues;

import com.qflow.Qflow.core.usecase.GetNextPatientUseCase;
import com.qflow.Qflow.infra.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/triage-queue")
public class TriageQueueController {
    private final GetNextPatientUseCase getNextPatientUseCase;

    @PostMapping
    public ResponseEntity addPatientToQueue(@AuthenticationPrincipal MyUserDetails userDetails, Long patientId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getNextPatient(@AuthenticationPrincipal MyUserDetails userDetails) {

        this.getNextPatientUseCase.execute(userDetails.getUser().getTenantId());
        return ResponseEntity.ok().build();
    }

    @Autowired
    public TriageQueueController(GetNextPatientUseCase getNextPatientUseCase) {
        this.getNextPatientUseCase = getNextPatientUseCase;
    }
}
