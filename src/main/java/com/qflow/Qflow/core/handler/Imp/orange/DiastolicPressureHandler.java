package com.qflow.Qflow.core.handler.Imp.orange;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class DiastolicPressureHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.diastolicPressure() > 110) return ManchesterPriority.VERY_URGENT; /*MAIOR QUE 110 mmHg*/

        else if (this.next != null) {
            return this.next.handleRequest(form);
        }

        return null;
    }
}
