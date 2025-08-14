package com.designpatterns.behavior.TemplateMethod;

public class Main {
    public static void main(String[] args) {
        AbstractSetting setting1 = new LocalSetting();
        System.out.println("test = " + setting1.getSetting("test"));

//        AbstractSetting setting2 = new RedisSetting();
//        System.out.println("autosave = " + setting2.getSetting("autosave"));

    }
}
/*
test =
 */

/*
模板方法
定义一个操作中的算法的骨架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。


 */