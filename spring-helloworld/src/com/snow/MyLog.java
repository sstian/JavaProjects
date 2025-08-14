package com.snow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {

    private static final Logger log = LoggerFactory.getLogger(MyLog.class);

    public static void main(String[] args) {
        System.out.println("hhh");
        log.info("hello, an info");
        log.warn("hello, a warn");
    }
}
