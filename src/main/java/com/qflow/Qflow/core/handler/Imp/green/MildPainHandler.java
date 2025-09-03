package com.qflow.Qflow.core.handler.Imp.green;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class MildPainHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.mildPain()) return ManchesterPriority.STANDARD;
        else if (this.next != null) {
            return this.next.handleRequest(form);
        }

        return null;
    }
}
