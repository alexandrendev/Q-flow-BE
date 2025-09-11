package com.qflow.Qflow.core.usecase;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.Imp.blue.ChronicIssueHandler;
import com.qflow.Qflow.core.handler.Imp.green.MildPainHandler;
import com.qflow.Qflow.core.handler.Imp.green.MildSymptomsStableHandler;
import com.qflow.Qflow.core.handler.Imp.orange.*;
import com.qflow.Qflow.core.handler.Imp.red.ImmediateDeathRiskHandler;
import com.qflow.Qflow.core.handler.Imp.red.*;
import com.qflow.Qflow.core.handler.Imp.yellow.ModerateBleedingHandler;
import com.qflow.Qflow.core.handler.Imp.yellow.ModeratePainHandler;
import com.qflow.Qflow.core.handler.Imp.yellow.TemperatureHandler;
import com.qflow.Qflow.core.handler.Imp.yellow.TraumaModerate;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;
import com.qflow.Qflow.core.ports.PatientRepository;

public class SuggestPriorityUseCase {
    private final PatientRepository repository;

    public SuggestPriorityUseCase(PatientRepository repository) {
        this.repository = repository;
    }

    public ManchesterPriority execute(TriageForm form, Long patientId) {
        var chain = buildChain();
        var priority = chain.handleRequest(form);

        repository.setSuggestedPriority(priority, patientId);

        return priority;
    }

    private PrioritySuggestionHandler buildChain() {
        var h1 = new ImmediateDeathRiskHandler();
        var h2 = new CardiorespiratoryArrestHandler();
        var h3 = new SevereRespiratoryDistressHandler();
        var h4 = new GlasgowCriticalHandler();
        var h5 = new SevereTraumaHandler();
        var h6 = new ChestPainSevereHandler();
        var h7 = new ConvulsionHandler();
        var h8 = new OxygenSaturationHandler();
        var h9 = new SystolicPressureHandler();
        var h10 = new DiastolicPressureHandler();
        var h11 = new HeartRateHandler();
        var h12 = new RespiratoryRateHandler();
        var h13 = new ModeratePainHandler();
        var h14 = new ModerateBleedingHandler();
        var h15 = new TemperatureHandler();
        var h16 = new TraumaModerate();
        var h17 = new MildPainHandler();
        var h18 = new MildSymptomsStableHandler();
        var h19 = new ChronicIssueHandler();

        h1.setNext(h2)
                .setNext(h3)
                .setNext(h4)
                .setNext(h5)
                .setNext(h6)
                .setNext(h7)
                .setNext(h8)
                .setNext(h9)
                .setNext(h10)
                .setNext(h11)
                .setNext(h12)
                .setNext(h13)
                .setNext(h14)
                .setNext(h15)
                .setNext(h16)
                .setNext(h17)
                .setNext(h18)
                .setNext(h19);

        return h1;
    }

}
