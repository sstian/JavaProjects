package com.designpatterns.behavior.Observer;

public class Main {
    public static void main(String[] args) {
        // observer:
        Admin a = new Admin();
        Customer c = new Customer();
        // store:
        Store store = new Store();
        // register:
        store.addObserver(a);
        store.addObserver(c);
        // 注册匿名观察者:
        store.addObserver(new ProductObserver() {
            @Override
            public void onPublished(Product product) {
                System.out.println("[Anonymous] on product published: " + product);
            }

            @Override
            public void onPriceChanged(Product product) {
                System.out.println("[Anonymous] on product price changed: " + product);
            }
        });
        // operation:
        store.addNewProduct("Design Patterns", 35.6);
        store.addNewProduct("Effective Java", 50.8);
        store.setProductPrice("Design Patterns", 31.9);

    }
}
/*
[Admin] on product published: {Product: name=Design Patterns, price=35.6}
[Customer] on product published: {Product: name=Design Patterns, price=35.6}
[Anonymous] on product published: {Product: name=Design Patterns, price=35.6}
[Admin] on product published: {Product: name=Effective Java, price=50.8}
[Customer] on product published: {Product: name=Effective Java, price=50.8}
[Anonymous] on product published: {Product: name=Effective Java, price=50.8}
[Admin] on product price changed: {Product: name=Design Patterns, price=31.9}
[Customer] on product price changed: {Product: name=Design Patterns, price=31.9}
[Anonymous] on product price changed: {Product: name=Design Patterns, price=31.9}
 */

/*
观察者 / 发布-订阅模式
定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。

┌─────────┐      ┌───────────────┐
│  Store  │─ ─ ─▶│ProductObserver│
└─────────┘      └───────────────┘
     │                   ▲
                         │
     │             ┌─────┴─────┐
     ▼             │           │
┌─────────┐   ┌─────────┐ ┌─────────┐
│ Product │   │  Admin  │ │Customer │ ...
└─────────┘   └─────────┘ └─────────┘
 */