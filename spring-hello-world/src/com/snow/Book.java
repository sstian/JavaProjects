package com.snow;

public class Book {
    private String address;

    public Book(String address) {
        this.address = address;
    }

    public void test() {
        System.out.println("address = " + address);
    }

    @Override
    public String toString() {
        return "Book{" +
                "address='" + address + '\'' +
                '}';
    }
}
