package com.snow.webflux.function;

public interface GreetingInterface {
    // 接口的抽象方法
    void sayMessage(String message);

    // 接口 默认方法，解决接口的修改与现有的实现不兼容的问题
    default void greet(){
        System.out.println("Wonderful!");
    }
}
