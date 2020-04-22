package com.huarun.OtherStructure;

//需要考虑学生，老师，管理员三个角色！！！

import com.huarun.pojo.ClassDO;
import com.huarun.pojo.MajorDO;
import com.huarun.pojo.StudentDO;

public class FaceUserInfo {
    private String id;
    private String name;
    private int major_id;
    private String major_name;
    private String major_desc;
    private int class_id;
    private String class_name;

    public FaceUserInfo(StudentDO studentDO, MajorDO majorDO, ClassDO classDO) {
        this.id = studentDO.getId();
        this.name = studentDO.getName();
        this.major_id = studentDO.getMajor_id();
        this.major_name = majorDO.getMajor_name();
        this.major_desc = majorDO.getMajor_desc();
        this.class_id = studentDO.getClass_id();
        this.class_name = classDO.getClass_name();
    }

    public FaceUserInfo(String id, String name, int major_id, String major_name, String major_desc, int class_id, String class_name) {
        this.id = id;
        this.name = name;
        this.major_id = major_id;
        this.major_name = major_name;
        this.major_desc = major_desc;
        this.class_id = class_id;
        this.class_name = class_name;
    }

    public FaceUserInfo() {
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

    public int getMajor_id() {
        return major_id;
    }

    public void setMajor_id(int major_id) {
        this.major_id = major_id;
    }

    public String getMajor_name() {
        return major_name;
    }

    public void setMajor_name(String major_name) {
        this.major_name = major_name;
    }

    public String getMajor_desc() {
        return major_desc;
    }

    public void setMajor_desc(String major_desc) {
        this.major_desc = major_desc;
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

    @Override
    public String toString() {
        return "FaceUserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", major_id=" + major_id +
                ", major_name='" + major_name + '\'' +
                ", major_desc='" + major_desc + '\'' +
                ", class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
