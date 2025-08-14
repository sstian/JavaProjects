package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.AbstractFactory;
import com.designpatterns.creation.AbstractFactory.HtmlDocument;
import com.designpatterns.creation.AbstractFactory.WordDocument;

// 实际工厂:
public class GoodFactory implements AbstractFactory {
    public HtmlDocument createHtml(String md) {
        return new GoodHtmlDocument(md);
    }

    public WordDocument createWord(String md) {
        return new GoodWordDocument(md);
    }
}
