package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.HtmlDocument;

import java.io.IOException;
import java.nio.file.Path;

public class FastHtmlDocument implements HtmlDocument {
    public FastHtmlDocument(String md) {
    }

    @Override
    public String toHtml() {
        System.out.println("FastHtmlDocument - toHtml()");
        return "toHtml";
    }

    @Override
    public void save(Path path) throws IOException {
        System.out.println("FastHtmlDocument - save(Path path)");
    }
}
