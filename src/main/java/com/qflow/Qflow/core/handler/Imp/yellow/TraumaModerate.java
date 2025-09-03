package com.qflow.Qflow.core.handler.Imp.yellow;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class TraumaModerate extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.traumaModerate()) return ManchesterPriority.URGENT;
        else if (this.next != null) {
            return this.next.handleRequest(form);
        }

        return null;
    }
}
