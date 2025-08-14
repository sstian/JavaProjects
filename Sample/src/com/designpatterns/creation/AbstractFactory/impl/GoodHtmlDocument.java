package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.HtmlDocument;

import java.io.IOException;
import java.nio.file.Path;

// 实际产品:
public class GoodHtmlDocument implements HtmlDocument {
    public GoodHtmlDocument(String md) {
    }

    @Override
    public String toHtml() {
        System.out.println("GoodHtmlDocument - toHtml()");
        return "";
    }

    @Override
    public void save(Path path) throws IOException {
        System.out.println("GoodHtmlDocument - save(Path path)");
    }
}
