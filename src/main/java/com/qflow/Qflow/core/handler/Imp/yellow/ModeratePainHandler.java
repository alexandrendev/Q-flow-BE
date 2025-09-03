package com.qflow.Qflow.core.handler.Imp.yellow;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class ModeratePainHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.moderatePain()) return ManchesterPriority.URGENT;

        else if (this.next != null) {
            return this.next.handleRequest(form);
        }

        return null;
    }
}
