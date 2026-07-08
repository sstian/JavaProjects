package com.designpattern.structure.Bridge.impl;

import com.designpattern.structure.Bridge.Engine;
import com.designpattern.structure.Bridge.RefinedCar;

public class BossCar extends RefinedCar {
    public BossCar(Engine engine) {
        super(engine);
    }

    public String getBrand() {
        return "Boss";
    }
}
