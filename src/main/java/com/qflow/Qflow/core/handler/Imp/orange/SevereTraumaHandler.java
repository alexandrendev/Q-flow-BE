package com.qflow.Qflow.core.handler.Imp.orange;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class SevereTraumaHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.severeTrauma()){
            return ManchesterPriority.VERY_URGENT;
        } else if (next != null) {
            return next.handleRequest(form);
        } else {
            return null;
        }
    }
}
