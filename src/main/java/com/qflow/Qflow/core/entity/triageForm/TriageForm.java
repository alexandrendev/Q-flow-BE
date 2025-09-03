package com.qflow.Qflow.core.entity.triageForm;

public record TriageForm(
        /*RISCO IMEDIATO*/
        boolean immediateDeathRisk,
        boolean cardiorespiratoryArrest,

        /*SINAIS DE ALERTA GRAVES*/
        boolean severeRespiratoryDistress,
        boolean criticalGlasgowScore,
        boolean severeTrauma,
        boolean chestPainSevere,
        boolean convulsion,
        double oxygenSaturation,       // ex: < 90% é grave
        int systolicPressure,          // pressão sistólica
        int diastolicPressure,         // pressão diastólica
        int heartRate,
        int respiratoryRate,

        // Sinais moderados
        boolean moderatePain,
        boolean moderateBleeding,
        double temperature,            // febre alta
        boolean traumaModerate,

        // Sintomas leves
        boolean mildPain,
        boolean mildFever,
        boolean mildSymptomsStable,

        // Queixas crônicas ou administrativas
        boolean chronicIssue
) {}