package com.huarun.pojo;

import java.io.Serializable;

public class TestInfo implements Serializable {
    private int course_id;
    private String course_name;

    public TestInfo(int course_id, String course_name) {
        this.course_id = course_id;
        this.course_name = course_name;
    }

    public TestInfo() {
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                '}';
    }
}
