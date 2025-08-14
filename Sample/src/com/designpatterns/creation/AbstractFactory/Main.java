package com.designpatterns.creation.AbstractFactory;

import com.designpatterns.creation.AbstractFactory.impl.FastFactory;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        // 创建AbstractFactory，实际类型是FastFactory
        // AbstractFactory factory = new FastFactory();
        AbstractFactory factory = AbstractFactory.createFactory("fast");
        // 生成Html文档:
        HtmlDocument html = factory.createHtml("#Hello\nHello, world!");
        html.save(Paths.get(".", "fast.html"));
        // 生成Word文档:
        WordDocument word = factory.createWord("#Hello\nHello, world!");
        word.save(Paths.get(".", "fast.doc"));

        //FastHtmlDocument - save(Path path)
        //FastWordDocument - save(Path path)
    }
}

/*
抽象工厂
提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。

                                ┌────────┐
                             ─ ▶│ProductA│
┌────────┐    ┌─────────┐   │   └────────┘
│ Client │─ ─▶│ Factory │─ ─
└────────┘    └─────────┘   │   ┌────────┐
                   ▲         ─ ▶│ProductB│
           ┌───────┴───────┐    └────────┘
           │               │
      ┌─────────┐     ┌─────────┐
      │Factory1 │     │Factory2 │
      └─────────┘     └─────────┘
           │   ┌─────────┐ │   ┌─────────┐
            ─ ▶│ProductA1│  ─ ▶│ProductA2│
           │   └─────────┘ │   └─────────┘
               ┌─────────┐     ┌─────────┐
           └ ─▶│ProductB1│ └ ─▶│ProductB2│
               └─────────┘     └─────────┘

 */