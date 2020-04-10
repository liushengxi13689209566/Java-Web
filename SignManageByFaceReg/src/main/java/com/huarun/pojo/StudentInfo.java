package com.huarun.pojo;

import java.io.Serializable;

public class StudentInfo implements Serializable {
    private int pri_id;
    private String id;
    private String name;
    private String password;
    private int class_id;

    public StudentInfo(int pri_id, String id, String name, String password, int class_id) {
        this.pri_id = pri_id;
        this.id = id;
        this.name = name;
        this.password = password;
        this.class_id = class_id;
    }

    public StudentInfo() {
    }

    public int getPri_id() {
        return pri_id;
    }

    public void setPri_id(int pri_id) {
        this.pri_id = pri_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "pri_id=" + pri_id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", class_id=" + class_id +
                '}';
    }
}
