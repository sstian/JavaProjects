package com.designpatterns.behavior.Mediator;

public class Main {
    public static void main(String[] args) {
        new OrderFrame("Hanburger", "Nugget", "Chip", "Coffee");
    }
}


/*
中介者
用一个中介对象来封装一系列的对象交互。中介者使各个对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。

            ┌─────────────────┐
     ┌─────▶│  CheckBox List  │
     │      └─────────────────┘
     │      ┌─────────────────┐
     │ ┌───▶│SelectAll Button │
     ▼ ▼    └─────────────────┘
┌─────────┐
│Mediator │
└─────────┘
     ▲ ▲    ┌─────────────────┐
     │ └───▶│SelectNone Button│
     │      └─────────────────┘
     │      ┌─────────────────┐
     └─────▶│ Inverse Button  │
            └─────────────────┘
 */