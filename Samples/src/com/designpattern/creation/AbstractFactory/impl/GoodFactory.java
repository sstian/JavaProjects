package com.designpattern.creation.AbstractFactory.impl;

import com.designpattern.creation.AbstractFactory.AbstractFactory;
import com.designpattern.creation.AbstractFactory.HtmlDocument;
import com.designpattern.creation.AbstractFactory.WordDocument;

// 实际工厂:
public class GoodFactory implements AbstractFactory {
    public HtmlDocument createHtml(String md) {
        return new GoodHtmlDocument(md);
    }

    public WordDocument createWord(String md) {
        return new GoodWordDocument(md);
    }
}
