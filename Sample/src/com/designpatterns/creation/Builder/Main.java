package com.designpatterns.creation.Builder;

public class Main {
    public static void main(String[] args) {
        HtmlBuilder builder = new HtmlBuilder();
        String result = builder.toHtml("#Hello\nHello, world!");
        System.out.println(result);

        //<h1>Hello</h1>
    }
}

/*
生成器
将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

 */
