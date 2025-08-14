package com.designpatterns.behavior.Interpreter;

public class Main {
    public static void main(String[] args) {
        String s = "+861012345678";
        System.out.println(s.matches("^\\+\\d+$"));

        //true
    }
}

/*
解释器
给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。


 */