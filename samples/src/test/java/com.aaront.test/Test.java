package com.aaront.test;


import java.util.List;

public class Test {
    private List<String> name;

    public Test(List<String> name) {
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}