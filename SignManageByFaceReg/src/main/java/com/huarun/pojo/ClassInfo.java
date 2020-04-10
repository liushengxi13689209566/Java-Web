package com.huarun.pojo;

import java.io.Serializable;

public class ClassInfo implements Serializable {
    private int class_id;
    private String class_name;
    private int major_id;

    public ClassInfo(int class_id, String class_name, int major_id) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.major_id = major_id;
    }

    public ClassInfo() {
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", major_id=" + major_id +
                '}';
    }
}
