package com.designpattern.structure.Decorator.impl;

import com.designpattern.structure.Decorator.TextNode;

public class SpanNode implements TextNode {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return "<span>" + text + "</span>";
    }
}
