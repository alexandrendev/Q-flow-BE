package com.qflow.Qflow.api.responses;

public enum Status {
    WAITING(1, "Waiting"),
    IN_PROGRESS(2, "In Progress"),
    COMPLETED(3, "Completed"),
    CANCELLED(4, "Cancelled");

    int value;
    String description;

    Status(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
