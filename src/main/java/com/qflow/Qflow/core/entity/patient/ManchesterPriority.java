package com.qflow.Qflow.core.entity.patient;

public enum ManchesterPriority {
    IMMEDIATE("red", "Immediate", 1),

    VERY_URGENT("orange", "Very Urgent", 2),

    URGENT("yellow", "Urgent", 3),

    STANDARD("green", "Standard", 4),

    NON_URGENT("blue", "Non-Urgent", 5);

    private String color;
    private String name;
    private int value;

    ManchesterPriority(String color, String name, int value) {
        this.color = color;
        this.name = name;
        this.value = value;
    }
}
