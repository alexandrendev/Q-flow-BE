package com.qflow.Qflow.core.handler.Imp.blue;

import com.qflow.Qflow.core.entity.patient.ManchesterPriority;
import com.qflow.Qflow.core.entity.triageForm.TriageForm;
import com.qflow.Qflow.core.handler.PrioritySuggestionHandler;

public class ChronicIssueHandler extends PrioritySuggestionHandler {
    @Override
    public ManchesterPriority handleRequest(TriageForm form) {
        if (form.chronicIssue()) return ManchesterPriority.NON_URGENT;
        else if (this.next != null) {
            return this.next.handleRequest(form);
        }
        return null;
    }
}
