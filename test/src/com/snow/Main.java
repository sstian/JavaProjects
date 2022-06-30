package com.snow;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static void print(List al2) {
        al2.add(2);
        al2 = new ArrayList();
        al2.add(3);
        al2.add(4);
    }

    public static void main(String[] args) {
	    List al1 = new ArrayList();
	    al1.add(1);
	    print(al1);
        System.out.println(al1.get(1));
    }
}
