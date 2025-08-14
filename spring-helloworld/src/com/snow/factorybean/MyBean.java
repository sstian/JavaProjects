package com.snow.factorybean;

import com.snow.Book;
import org.springframework.beans.factory.FactoryBean;

public class MyBean implements FactoryBean<Book> {

    @Override
    public Book getObject() throws Exception {
        Book book = new Book("Shanghai");
        return book;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
