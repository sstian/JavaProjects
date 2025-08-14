package com.designpatterns.behavior.Iterator;

public class Main {
    public static void main(String[] args) {
        ReverseArrayCollection<Integer> collection = new ReverseArrayCollection<>(1, 2, 3);
        for (Integer ele : collection) {
            System.out.println(ele);
        }
    }
}
/*
3
2
1
 */

/*
迭代器
提供一种方法顺序访问一个聚合对象中的各个元素，而又不需要暴露该对象的内部表示。


 */