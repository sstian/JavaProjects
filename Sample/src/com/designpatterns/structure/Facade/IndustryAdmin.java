package com.designpatterns.structure.Facade;

// 工商注册:
public class IndustryAdmin {
    public Company register(String name) {
        System.out.println("IndustryAdmin.register");
        return new Company();
    }
}
