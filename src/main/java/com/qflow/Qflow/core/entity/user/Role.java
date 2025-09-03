package com.qflow.Qflow.core.entity.user;

public enum Role {
    RECEPTIONIST("receptionist", 1),
    NURSE("nurse", 2),
    DOCTOR("doctor", 3),
    ;

    private String roleName;
    private int value;

    Role(String roleName, int value) {
        this.roleName = roleName;
        this.value = value;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static Role fromString(String roleString) {
        if (roleString == null) return null;

        for (Role role : Role.values()) {
            if (role.roleName.equalsIgnoreCase(roleString) ||
                    role.name().equalsIgnoreCase(roleString)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for role: " + roleString);
    }
}
