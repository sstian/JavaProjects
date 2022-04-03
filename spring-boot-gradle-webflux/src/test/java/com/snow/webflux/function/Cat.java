package com.snow.webflux.function;

public class Cat {

    private String name = "Totoro";

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public static void bark(Cat cat) {
        System.out.println(cat + " meow");
    }

    // 默认会把当前实例传到非静态方法，参数名为this,位置在第一个
    public int eat(int num) {
        System.out.println("eat " + num + " fish");
        return num;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
