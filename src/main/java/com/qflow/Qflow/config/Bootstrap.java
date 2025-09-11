package com.qflow.Qflow.config;

import com.qflow.Qflow.core.ports.PatientRepository;
import com.qflow.Qflow.core.usecase.SetManchesterPriorityUseCase;
import com.qflow.Qflow.core.usecase.SuggestPriorityUseCase;
import com.qflow.Qflow.infra.repository.patient.PatientRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bootstrap {

    @Bean
    public SetManchesterPriorityUseCase setPatientSuggestedPriorityUseCase(PatientRepository repo) {
        return new SetManchesterPriorityUseCase(repo);
    }

    @Bean
    public SuggestPriorityUseCase suggestPriorityUseCase(PatientRepositoryImpl repository) {
        return new SuggestPriorityUseCase(repository);
    }


}
