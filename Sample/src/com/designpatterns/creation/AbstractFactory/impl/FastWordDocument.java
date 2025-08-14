package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.WordDocument;

import java.io.IOException;
import java.nio.file.Path;

public class FastWordDocument implements WordDocument {
    public FastWordDocument(String md) {
    }

    @Override
    public void save(Path path) throws IOException {
        System.out.println("FastWordDocument - save(Path path)");
    }
}
