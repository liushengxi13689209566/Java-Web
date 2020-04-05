package com.huarun.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class TestInfo implements Serializable {
    private int id;
    private String name;
    private byte[] val;

    public TestInfo(int id, String name, byte[] val) {
        this.id = id;
        this.name = name;
        this.val = val;
    }

    public TestInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getVal() {
        return val;
    }

    public void setVal(byte[] val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", val=" + Arrays.toString(val) +
                '}';
    }
}
