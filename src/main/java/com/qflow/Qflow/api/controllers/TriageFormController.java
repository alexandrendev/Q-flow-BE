package com.qflow.Qflow.api.controllers;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.usecase.SuggestPriorityUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage-form")
public class TriageFormController {
    private final SuggestPriorityUseCase useCase;



    @PostMapping
    public ResponseEntity<ManchesterPriority> getSuggestedManchesterPriority(
            @RequestBody TriageForm form,
            @RequestParam Long patientId
    ) {

        ManchesterPriority priority = useCase.execute(form, patientId);
        if (priority == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(priority);
    }

    public TriageFormController(SuggestPriorityUseCase useCase) {
        this.useCase = useCase;
    }
}
