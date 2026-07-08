package com.designpattern.structure.Decorator.impl;

import com.designpattern.structure.Decorator.NodeDecorator;
import com.designpattern.structure.Decorator.TextNode;

public class UnderlineDecorator extends NodeDecorator {
    public UnderlineDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<u>" + target.getText() + "</u>";
    }
}

