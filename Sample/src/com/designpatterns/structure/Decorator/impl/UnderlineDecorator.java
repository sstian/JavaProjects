package com.designpatterns.structure.Decorator.impl;

import com.designpatterns.structure.Decorator.NodeDecorator;
import com.designpatterns.structure.Decorator.TextNode;

public class UnderlineDecorator extends NodeDecorator {
    public UnderlineDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<u>" + target.getText() + "</u>";
    }
}

