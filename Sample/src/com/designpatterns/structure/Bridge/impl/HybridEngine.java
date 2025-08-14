package com.designpatterns.structure.Bridge.impl;

import com.designpatterns.structure.Bridge.Engine;

public class HybridEngine implements Engine {
    public void start() {
        System.out.println("Start Hybrid Engine...");
    }
}
