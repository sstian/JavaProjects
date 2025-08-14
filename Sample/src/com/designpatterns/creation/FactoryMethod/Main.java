package com.designpatterns.creation.FactoryMethod;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        NumberFactory factory = NumberFactory.getFactory();
        Number result = factory.parse("123.456");
        System.out.println(result);

        Number myResult = MyNumberFactory.parse("123.456");
        System.out.println(myResult);

        //123.456
        //123.456
    }

    // 静态工厂方法（Static Factory Method），更常用
    public static class MyNumberFactory {
        public static Number parse(String s) {
            return new BigDecimal(s);
        }
    }
}

/*
工厂方法
定义一个用于创建对象的接口，让子类决定实例化哪一个类。Factory Method使一个类的实例化延迟到其子类。

┌─────────────┐      ┌─────────────┐
│   Product   │      │   Factory   │
└─────────────┘      └─────────────┘
       ▲                    ▲
       │                    │
┌─────────────┐      ┌─────────────┐
│ ProductImpl │◀─ ─ ─│ FactoryImpl │
└─────────────┘      └─────────────┘

 */