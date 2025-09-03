package com.qflow.Qflow.core.handler.Imp.orange;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class OxygenSaturationHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.oxygenSaturation() > 90) return ManchesterPriority.VERY_URGENT;

        else if (this.next != null) {
            return this.next.handleRequest(form);
        }
        return null;
    }
}
