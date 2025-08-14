package com.designpatterns.structure.Flyweight;

public class Main {
    public static void main(String[] args) {
        Student stu = Student.create(1, "Jack");
        System.out.println(stu);
    }
}
/*
create new Student(1, Jack)
com.designpatterns.structure.Flyweight.Student@66a29884
 */

/*
享元
运用共享技术有效地支持大量细粒度的对象。

总是使用工厂方法而不是new操作符创建实例，可获得享元模式的好处。

 */