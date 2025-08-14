package com.designpatterns.structure.Decorator.impl;

import com.designpatterns.structure.Decorator.NodeDecorator;
import com.designpatterns.structure.Decorator.TextNode;

public class BoldDecorator extends NodeDecorator {
    public BoldDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<b>" + target.getText() + "</b>";
    }
}
