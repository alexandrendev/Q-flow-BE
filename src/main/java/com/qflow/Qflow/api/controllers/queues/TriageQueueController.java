package com.qflow.Qflow.api.controllers.queues;

import com.qflow.Qflow.api.responses.TriageQueueResponse;
import com.qflow.Qflow.core.ports.TriageQueueRepository;
import com.qflow.Qflow.core.usecase.GetNextPatientUseCase;
import com.qflow.Qflow.infra.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/triage-queue")
public class TriageQueueController {
    private final GetNextPatientUseCase getNextPatientUseCase;
    private final TriageQueueRepository triageQueueRepository;

    @PostMapping
    public ResponseEntity addPatientToQueue(@AuthenticationPrincipal MyUserDetails userDetails, Long patientId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getNextPatient(@AuthenticationPrincipal MyUserDetails userDetails) {

        this.getNextPatientUseCase.execute(userDetails.getUser().getTenantId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<TriageQueueResponse>> listWaitingPatients(@AuthenticationPrincipal MyUserDetails userDetails) {
        Long tenantId = userDetails.getUser().getTenantId();
        List<TriageQueueResponse> waitingPatients = triageQueueRepository.getAllWaitingPatients(tenantId);
        return ResponseEntity.ok(waitingPatients);
    }

    @Autowired
    public TriageQueueController(GetNextPatientUseCase getNextPatientUseCase, TriageQueueRepository triageQueueRepository) {
        this.getNextPatientUseCase = getNextPatientUseCase;
        this.triageQueueRepository = triageQueueRepository;
    }
}
