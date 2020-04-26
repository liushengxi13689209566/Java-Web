package com.huarun.pojo;

import java.io.Serializable;

public class CourseMajorClassDO implements Serializable {
    private int course_id;
    private int major_id;
    private int class_id;

    public CourseMajorClassDO(int course_id, int major_id, int class_id) {
        this.course_id = course_id;
        this.major_id = major_id;
        this.class_id = class_id;
    }

    public CourseMajorClassDO() {
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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
        return "CourseMajorClassDO{" +
                "course_id=" + course_id +
                ", major_id=" + major_id +
                ", class_id=" + class_id +
                '}';
    }
}
