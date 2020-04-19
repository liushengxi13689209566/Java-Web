package com.huarun.OtherStructure;

import com.huarun.pojo.ClassDO;
import com.huarun.pojo.MajorDO;
import com.huarun.pojo.StudentDO;

import java.io.Serializable;

public class StudentInfoShow implements Serializable {
    private String id;
    private String name;
    private String major_name;
    private String class_name;

    public StudentInfoShow(StudentDO studentDO, ClassDO classDO, MajorDO majorDO) {
        this.id = studentDO.getId();
        this.name = studentDO.getName();
        this.major_name = majorDO.getMajor_name();
        this.class_name = classDO.getClass_name();
    }

    public StudentInfoShow(String id, String name, String major_name, String class_name) {
        this.id = id;
        this.name = name;
        this.major_name = major_name;
        this.class_name = class_name;
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

    @Override
    public String toString() {
        return "StudentInfoShow{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", major_name='" + major_name + '\'' +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
