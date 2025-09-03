package com.qflow.Qflow.core.handler;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;

public abstract class PrioritySuggestionHandler {
    protected PrioritySuggestionHandler next;

    public PrioritySuggestionHandler setNext(PrioritySuggestionHandler nextHandler) {
        this.next = nextHandler;
        return this.next;
    }

    public abstract ManchesterPriority handleRequest(TriageForm form);
}
