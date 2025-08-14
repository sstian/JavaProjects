package com.designpatterns.creation.Prototype;

public class Student implements Cloneable {
    private int id;
    private String name;
    private int score;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // 复制新对象并返回
    public Object clone() throws CloneNotSupportedException {
        Object o = super.clone();

        Student std = new Student();
        std.id = this.id;
        std.name = this.name;
        std.score = this.score;
        return std;
    }
}
