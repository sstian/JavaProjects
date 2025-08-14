package com.designpatterns.structure.Bridge.impl;

import com.designpatterns.structure.Bridge.Engine;
import com.designpatterns.structure.Bridge.RefinedCar;

public class BossCar extends RefinedCar {
    public BossCar(Engine engine) {
        super(engine);
    }

    public String getBrand() {
        return "Boss";
    }
}
