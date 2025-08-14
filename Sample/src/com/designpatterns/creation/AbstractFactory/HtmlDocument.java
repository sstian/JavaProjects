package com.designpatterns.creation.AbstractFactory;

import java.io.IOException;
import java.nio.file.Path;

// Html文档接口:
public interface HtmlDocument {
    String toHtml();
    void save(Path path) throws IOException;
}
