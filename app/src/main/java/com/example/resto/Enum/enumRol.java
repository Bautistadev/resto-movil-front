package com.example.resto.Enum;

public enum enumRol {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private int level;

    private enumRol(int theLevel) {
        this.level = theLevel;
    }

    public int getLevel() {
        return level;
    }
}
