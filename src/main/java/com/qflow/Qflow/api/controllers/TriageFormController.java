package com.qflow.Qflow.api.controllers;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.usecase.SuggestPriorityUseCase;
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
