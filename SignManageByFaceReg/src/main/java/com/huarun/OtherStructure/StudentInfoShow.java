package com.huarun.OtherStructure;

import com.huarun.pojo.ClassDO;
import com.huarun.pojo.MajorDO;
import com.huarun.pojo.StudentDO;

import java.io.Serializable;

public class StudentInfoShow implements Serializable {
    private String id;
    private String name;
    private String sex;
    private String major_name;
    private String class_name;
    private String identity_card;

    public StudentInfoShow(StudentDO studentDO, ClassDO classDO, MajorDO majorDO) {
        this.id = studentDO.getId();
        this.name = studentDO.getName();
        this.sex = studentDO.getSex();
        this.major_name = majorDO.getMajor_name();
        this.class_name = classDO.getClass_name();
        this.identity_card = studentDO.getIdentity_card();
    }

    public StudentInfoShow(String id, String name, String sex, String major_name, String class_name, String identity_card) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.major_name = major_name;
        this.class_name = class_name;
        this.identity_card = identity_card;
    }

    public StudentInfoShow() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    @Override
    public String toString() {
        return "StudentInfoShow{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", major_name='" + major_name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", identity_card='" + identity_card + '\'' +
                '}';
    }
}
