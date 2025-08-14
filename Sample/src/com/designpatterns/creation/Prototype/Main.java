package com.designpatterns.creation.Prototype;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student std1 = new Student();
        std1.setId(123);
        std1.setName("Bob");
        std1.setScore(88);
        // 复制新对象:
        Student std2 = (Student) std1.clone();
        System.out.println(std1);
        System.out.println(std2);
        System.out.println(std1 == std2);

        //com.designpatterns.creation.Prototype.Student@2f4d3709
        //com.designpatterns.creation.Prototype.Student@4e50df2e
        //false

    }
}

/*
原型
用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。

 */