package com.snow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
    private String[] courses;
    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;

    private List<String> lists;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + Arrays.toString(courses) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                ", lists=" + lists +
                '}';
    }
}
