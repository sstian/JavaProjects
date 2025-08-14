package com.designpatterns.creation.Singleton;

public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();

        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
    }
}

/*
单例
保证一个类仅有一个实例，并提供一个访问它的全局访问点。

方法1：
1. 只有private构造方法，确保外部无法实例化；
2. 通过private static变量持有唯一实例，保证全局唯一性；
3. 通过public static方法返回此唯一实例，使外部调用方能获取到实例。

方法2：
利用Java的enum，因为Java保证枚举类的每个枚举都是单例，所以我们只需要编写一个只有一个枚举的类即可。

 */