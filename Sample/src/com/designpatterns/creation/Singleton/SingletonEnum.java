package com.designpatterns.creation.Singleton;

public enum SingletonEnum {
    // 唯一枚举:
    INSTANCE;

    private String name = "SingletonEnum";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
