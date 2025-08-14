package com.designpatterns.behavior.Observer;

public interface ProductObserver {

    void onPublished(Product product);

    void onPriceChanged(Product product);
}

