package com.qflow.Qflow.core.entity.patient;

public class Patient {
    private String name;
    private ManchesterPriority suggestedPriority;
    private ManchesterPriority AssingnedPriority;

    public Patient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManchesterPriority getSuggestedPriority() {
        return suggestedPriority;
    }

    public void setSuggestedPriority(ManchesterPriority suggestedPriority) {
        this.suggestedPriority = suggestedPriority;
    }

    public ManchesterPriority getAssingnedPriority() {
        return AssingnedPriority;
    }

    public void setAssingnedPriority(ManchesterPriority assingnedPriority) {
        AssingnedPriority = assingnedPriority;
    }
}
