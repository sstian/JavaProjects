package com.designpattern.creation.AbstractFactory.impl;

import com.designpattern.creation.AbstractFactory.HtmlDocument;
import com.designpattern.creation.AbstractFactory.WordDocument;

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
