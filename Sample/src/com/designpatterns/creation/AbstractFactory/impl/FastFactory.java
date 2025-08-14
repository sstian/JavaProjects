package com.designpatterns.creation.AbstractFactory.impl;

import com.designpatterns.creation.AbstractFactory.AbstractFactory;
import com.designpatterns.creation.AbstractFactory.HtmlDocument;
import com.designpatterns.creation.AbstractFactory.WordDocument;

public class FastFactory implements AbstractFactory {
    public HtmlDocument createHtml(String md) {
        return new FastHtmlDocument(md);
    }

    public WordDocument createWord(String md) {
        return new FastWordDocument(md);
    }
}

