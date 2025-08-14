package com.designpatterns.structure.Bridge;

import com.designpatterns.structure.Bridge.impl.BossCar;
import com.designpatterns.structure.Bridge.impl.HybridEngine;

public class Main {
    public static void main(String[] args) {
        RefinedCar car = new BossCar(new HybridEngine());
        car.drive();

        //Start Hybrid Engine...
        //Drive Boss car...
    }
}

/*
桥接
将抽象部分与它的实现部分分离，使它们都可以独立地变化。

       ┌───────────┐
       │    Car    │
       └───────────┘
             ▲
             │
       ┌───────────┐       ┌─────────┐
       │RefinedCar │ ─ ─ ─▶│ Engine  │
       └───────────┘       └─────────┘
             ▲                  ▲
    ┌────────┼────────┐         │ ┌──────────────┐
    │        │        │         ├─│  FuelEngine  │
┌───────┐┌───────┐┌───────┐     │ └──────────────┘
│BigCar ││TinyCar││BossCar│     │ ┌──────────────┐
└───────┘└───────┘└───────┘     ├─│ElectricEngine│
                                │ └──────────────┘
                                │ ┌──────────────┐
                                └─│ HybridEngine │
                                  └──────────────┘

 */