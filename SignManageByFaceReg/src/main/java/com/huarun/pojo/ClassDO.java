package com.huarun.pojo;

import java.io.Serializable;

public class ClassDO implements Serializable {
    private int class_id;
    private String class_name;
    private int class_desc;

    public ClassDO(int class_id, String class_name, int class_desc) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.class_desc = class_desc;
    }

    public ClassDO() {
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

    public int getClass_desc() {
        return class_desc;
    }

    public void setClass_desc(int class_desc) {
        this.class_desc = class_desc;
    }

    @Override
    public String toString() {
        return "ClassDO{" +
                "class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", class_desc=" + class_desc +
                '}';
    }
}
