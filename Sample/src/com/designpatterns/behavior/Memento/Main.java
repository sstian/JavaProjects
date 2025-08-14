package com.designpatterns.behavior.Memento;

public class Main {
    public static void main(String[] args) {
    TextEditor editor = new TextEditor();
    editor.delete();
    }
}


/*
备忘录
在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。

标准的备忘录模式有这么几种角色：
Memento：存储的内部状态；
Originator：创建一个备忘录并设置其状态；
Caretaker：负责保存备忘录。
 */