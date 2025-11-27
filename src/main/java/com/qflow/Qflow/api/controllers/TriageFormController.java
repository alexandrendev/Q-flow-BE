package com.qflow.Qflow.api.controllers;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.usecase.SuggestPriorityUseCase;
import com.qflow.Qflow.api.responses.triage.TriageQuestion;
import java.util.List;
import java.util.Collections;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage-form")
public class TriageFormController {
    private final SuggestPriorityUseCase useCase;

    private static final List<TriageQuestion> QUESTIONS = Collections.unmodifiableList(List.of(
            new TriageQuestion("immediateDeathRisk","Risco de morte imediata","IMMEDIATE","boolean","Paciente em risco imediato de morte."),
            new TriageQuestion("cardiorespiratoryArrest","Parada cardiorrespiratória","IMMEDIATE","boolean","Necessita reanimação imediata."),

            new TriageQuestion("severeRespiratoryDistress","Dispneia grave","SEVERE","boolean","Insuficiência respiratória ou grande esforço ventilatório."),
            new TriageQuestion("criticalGlasgowScore","Rebaixamento grave de consciência","SEVERE","boolean","Escala de Glasgow crítica."),
            new TriageQuestion("severeTrauma","Trauma grave","SEVERE","boolean","Trauma com risco à vida ou múltiplos sistemas."),
            new TriageQuestion("chestPainSevere","Dor torácica intensa","SEVERE","boolean","Dor torácica sugestiva de evento agudo grave."),
            new TriageQuestion("convulsion","Convulsão","SEVERE","boolean","Convulsão atual ou recente."),
            new TriageQuestion("oxygenSaturation","Saturação de O2","SEVERE","number","Valores < 90% são graves."),
            new TriageQuestion("systolicPressure","Pressão sistólica","SEVERE","number","Avaliação da pressão sistólica."),
            new TriageQuestion("diastolicPressure","Pressão diastólica","SEVERE","number","Avaliação da pressão diastólica."),
            new TriageQuestion("heartRate","Frequência cardíaca","SEVERE","number","Batimentos por minuto."),
            new TriageQuestion("respiratoryRate","Frequência respiratória","SEVERE","number","Respirações por minuto."),

            new TriageQuestion("moderatePain","Dor moderada","MODERATE","boolean","Dor que exige avaliação mas não é crítica."),
            new TriageQuestion("moderateBleeding","Sangramento moderado","MODERATE","boolean","Perda sanguínea controlável."),
            new TriageQuestion("temperature","Temperatura","MODERATE","number","Febre elevada ou hipotermia."),
            new TriageQuestion("traumaModerate","Trauma moderado","MODERATE","boolean","Trauma sem risco imediato."),

            new TriageQuestion("mildPain","Dor leve","MILD","boolean","Desconforto leve."),
            new TriageQuestion("mildFever","Febre baixa","MILD","boolean","Temperatura discretamente elevada."),
            new TriageQuestion("mildSymptomsStable","Sintomas leves estáveis","MILD","boolean","Condição estável sem piora."),

            new TriageQuestion("chronicIssue","Queixa crônica/administrativa","CHRONIC","boolean","Problema crônico ou demanda administrativa.")
    ));



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

    @GetMapping("/questions")
    public ResponseEntity<List<TriageQuestion>> listQuestions() {
        return ResponseEntity.ok(QUESTIONS);
    }

    public TriageFormController(SuggestPriorityUseCase useCase) {
        this.useCase = useCase;
    }
}
