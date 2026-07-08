package com.designpattern.creation.AbstractFactory.impl;

import com.designpattern.creation.AbstractFactory.WordDocument;

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
