package com.qflow.Qflow.core.handler.Imp.orange;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class HeartRateHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if( form.heartRate() < 50 || form.heartRate() > 120) return ManchesterPriority.VERY_URGENT;

        else if (this.next != null) {
            return this.next.handleRequest(form);
        }
        return null;
    }
}
