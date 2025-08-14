package com.designpatterns.structure.Adapter;

import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) {
        Callable<Long> callable = new Task(123450000L);
        Runnable runnable = new RunnableAdapter(callable);
        Thread thread = new Thread(runnable); // compile error!
        thread.start();

        // Result: 7619951311725000
    }
}

/*
适配器
将一个类的接口转换成客户希望的另外一个接口，使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。



 */