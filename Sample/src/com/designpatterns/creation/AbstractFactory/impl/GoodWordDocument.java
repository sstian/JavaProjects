package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.HtmlDocument;
import com.designpatterns.creation.AbstractFactory.WordDocument;

import java.io.IOException;
import java.nio.file.Path;

public class GoodWordDocument implements WordDocument {

    public GoodWordDocument(String md) {
    }

    @Override
    public void save(Path path) throws IOException {
        System.out.println("GoodHtmlDocument - save(Path path)()");
    }
}
