package com.huarun.pojo;

import java.io.Serializable;

public class MajorClassDO implements Serializable {
    private int major_id;
    private int class_id;

    public MajorClassDO(int major_id, int class_id) {
        this.major_id = major_id;
        this.class_id = class_id;
    }

    public MajorClassDO() {
    }

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "MajorClassDO{" +
                "major_id=" + major_id +
                ", class_id=" + class_id +
                '}';
    }
}
