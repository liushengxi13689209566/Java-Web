package com.huarun.controller;

public class Test {
    boolean flag = true;

    public Test(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Test{" +
                "flag=" + flag +
                '}';
    }
}
