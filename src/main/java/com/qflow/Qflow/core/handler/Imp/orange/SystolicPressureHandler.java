package com.qflow.Qflow.core.handler.Imp.orange;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class SystolicPressureHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {

        if (form.systolicPressure() < 90 || form.systolicPressure() > 180) return ManchesterPriority.VERY_URGENT;

        else if (this.next != null) {
            return this.next.handleRequest(form);
        }

        return null;
    }
}
