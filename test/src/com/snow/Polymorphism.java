package com.snow;

import java.io.*;

public class Polymorphism {
    public static void main(String[] args) {
        Student.hello();
        InputStreamReader isr;
        OutputStreamWriter osw;
        PrintStream ps;
        PrintWriter pw;
        
    }

    class Person {
        static void hello() {
            System.out.println("Person.Hello");
        }
    }

    class Student extends Person {
        static void hello() {
            System.out.println("Student.Hello");
        }
    }
}
