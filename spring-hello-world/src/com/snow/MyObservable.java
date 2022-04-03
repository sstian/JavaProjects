package com.snow;

import java.util.Observable;

public class MyObservable extends Observable {

    public static void main(String[] args) {
        MyObservable my = new MyObservable();
        // 添加观察者
        my.addObserver((o,arg) ->{System.out.println("xiao ming is notified");});
        my.addObserver((o,arg) ->{System.out.println("li hua is notified");});
        // 数据变化，通知
        my.setChanged();
        my.notifyObservers();
    }
}
