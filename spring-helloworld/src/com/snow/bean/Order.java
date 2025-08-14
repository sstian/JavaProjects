package com.snow.bean;

public class Order {
    private String oname;

    public Order() {
        System.out.println("1. create instance");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("2. set attributes");
    }

    public void initMethod() {
        System.out.println("4. init method");
    }

    public void destroyMethod() {
        System.out.println("7. destroy method");
    }

    @Override
    public String toString() {
        return "Order{" +
                "oname='" + oname + '\'' +
                '}';
    }
}
