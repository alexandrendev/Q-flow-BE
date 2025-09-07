package com.qflow.Qflow.core.entity.patient;

import java.time.LocalDateTime;

public class Patient {
    private Long id;
    private String name;
    private Long tenantId;
    private ManchesterPriority suggestedPriority;
    private ManchesterPriority AssingnedPriority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Patient(String name) {
        this.name = name;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Patient() {
    }
}
