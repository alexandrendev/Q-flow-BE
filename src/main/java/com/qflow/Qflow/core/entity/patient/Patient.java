package com.qflow.Qflow.core.entity.patient;

import java.time.LocalDateTime;

public class Patient {
    private Long id;
    private String name;
    private Long tenantId;
    private ManchesterPriority suggestedPriority;
    private ManchesterPriority assignedPriority;
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

    public ManchesterPriority getAssignedPriority() {
        return assignedPriority;
    }

    public void setAssignedPriority(ManchesterPriority assignedPriority) {
        this.assignedPriority = assignedPriority;
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

    public Patient(Long id, String name, Long tenantId, ManchesterPriority suggestedPriority, ManchesterPriority assignedPriority, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.tenantId = tenantId;
        this.suggestedPriority = suggestedPriority;
        this.assignedPriority = assignedPriority;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
