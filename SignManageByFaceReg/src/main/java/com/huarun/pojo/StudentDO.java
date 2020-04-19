package com.huarun.pojo;

import java.io.Serializable;

public class StudentDO implements Serializable {
    private int pri_id;
    private String id;
    private String name;
    private String password;
    private int major_id;
    private int class_id;
    private String identity_card;
    private String sex;

    public StudentDO(int pri_id, String id, String name, String password, int major_id, int class_id, String identity_card, String sex) {
        this.pri_id = pri_id;
        this.id = id;
        this.name = name;
        this.password = password;
        this.major_id = major_id;
        this.class_id = class_id;
        this.identity_card = identity_card;
        this.sex = sex;
    }

    public StudentDO() {
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

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "StudentDO{" +
                "pri_id=" + pri_id +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", major_id=" + major_id +
                ", class_id=" + class_id +
                ", identity_card='" + identity_card + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
