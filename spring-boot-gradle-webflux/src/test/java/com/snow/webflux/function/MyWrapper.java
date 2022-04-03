package com.snow.webflux.function;

public class MyWrapper {
    public static String fromError(Throwable error){
        return "That is a new Error";
    }
}
