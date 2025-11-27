package com.qflow.Qflow.api.responses.triage;

public record TriageQuestion(
        String key,
        String label,
        String category,
        String type,
        String description
) {}
