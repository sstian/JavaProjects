package com.designpatterns.structure.Decorator;

import com.designpatterns.structure.Decorator.impl.BoldDecorator;
import com.designpatterns.structure.Decorator.impl.SpanNode;
import com.designpatterns.structure.Decorator.impl.UnderlineDecorator;

public class Main {
    public static void main(String[] args) {
        TextNode n1 = new SpanNode();
        TextNode n2 = new BoldDecorator(new UnderlineDecorator(new SpanNode()));
        n1.setText("Hello");
        n2.setText("Decorated");
        System.out.println(n1.getText());
        System.out.println(n2.getText());

        //<span>Hello</span>
        //<b><u><span>Decorated</span></u></b>
    }
}

/*
装饰器
动态地给一个对象添加一些额外的职责。就增加功能来说，相比生成子类更为灵活。

             ┌───────────┐
             │ Component │
             └───────────┘
                   ▲
      ┌────────────┼─────────────────┐
      │            │                 │
┌───────────┐┌───────────┐     ┌───────────┐
│ComponentA ││ComponentB │...  │ Decorator │
└───────────┘└───────────┘     └───────────┘
                                     ▲
                              ┌──────┴──────┐
                              │             │
                        ┌───────────┐ ┌───────────┐
                        │DecoratorA │ │DecoratorB │...
                        └───────────┘ └───────────┘

 */