package com.huarun.OtherStructure;

//需要考虑学生，老师，管理员三个角色！！！

import com.huarun.pojo.StudentDO;

public class FaceAddUserInfo {
    private String id;
    private String name;
    private int major_id;
    private String major_name;
    private int class_id;
    private String class_name;

    public FaceAddUserInfo(String id, String name, int major_id, String major_name, int class_id, String class_name) {
        this.id = id;
        this.name = name;
        this.major_id = major_id;
        this.major_name = major_name;
        this.class_id = class_id;
        this.class_name = class_name;
    }

    public FaceAddUserInfo(StudentDO studentDO, String major_name, String class_name) {
        this.id = studentDO.getId();
        this.name = studentDO.getName();
        this.major_id = studentDO.getMajor_id();
        this.major_name = major_name;
        this.class_id = studentDO.getClass_id();
        this.class_name = class_name;
    }

    public FaceAddUserInfo() {
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
        return "FaceAddUserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", major_id=" + major_id +
                ", major_name='" + major_name + '\'' +
                ", class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
