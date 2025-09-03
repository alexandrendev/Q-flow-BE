package com.qflow.Qflow.core.handler.Imp.red;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class CardiorespiratoryArrestHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.cardiorespiratoryArrest()) {
            return ManchesterPriority.IMMEDIATE;
        } else if (next != null) {
            return next.handleRequest(form);
        } else {
            return null;
        }
    }
}
