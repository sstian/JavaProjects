package com.designpattern.structure.Decorator.impl;

import com.designpattern.structure.Decorator.NodeDecorator;
import com.designpattern.structure.Decorator.TextNode;

public class BoldDecorator extends NodeDecorator {
    public BoldDecorator(TextNode target) {
        super(target);
    }

    public String getText() {
        return "<b>" + target.getText() + "</b>";
    }
}
