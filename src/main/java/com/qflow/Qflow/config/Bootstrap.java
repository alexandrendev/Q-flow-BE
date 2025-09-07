package com.qflow.Qflow.config;

import com.qflow.Qflow.core.ports.PatientRepository;
import com.qflow.Qflow.core.usecase.SetPatientSuggestedPriorityUseCase;
import com.qflow.Qflow.core.usecase.SuggestPriorityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bootstrap {

    @Bean
    public SetPatientSuggestedPriorityUseCase setPatientSuggestedPriorityUseCase(PatientRepository repo) {
        return new SetPatientSuggestedPriorityUseCase(repo);
    }

    @Bean
    public SuggestPriorityUseCase suggestPriorityUseCase(SetPatientSuggestedPriorityUseCase setPriority) {
        return new SuggestPriorityUseCase(setPriority);
    }
}
