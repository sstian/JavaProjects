package com.designpattern.creation.AbstractFactory.impl;

import com.designpattern.creation.AbstractFactory.AbstractFactory;
import com.designpattern.creation.AbstractFactory.HtmlDocument;
import com.designpattern.creation.AbstractFactory.WordDocument;

public class FastFactory implements AbstractFactory {
    public HtmlDocument createHtml(String md) {
        return new FastHtmlDocument(md);
    }

    public WordDocument createWord(String md) {
        return new FastWordDocument(md);
    }
}

