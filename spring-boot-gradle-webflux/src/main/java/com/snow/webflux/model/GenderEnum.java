package com.snow.webflux.model;

public enum GenderEnum {
    Male("male"),
    Female("female");

    private final String value;

    GenderEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
