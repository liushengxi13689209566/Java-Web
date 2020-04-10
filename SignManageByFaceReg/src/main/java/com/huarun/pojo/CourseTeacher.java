package com.huarun.pojo;

import java.io.Serializable;

public class CourseTeacher implements Serializable {
    private String teacher_id;
    private int course_id;

    public CourseTeacher(String teacher_id, int course_id) {
        this.teacher_id = teacher_id;
        this.course_id = course_id;
    }

    public CourseTeacher() {
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "CourseTeacher{" +
                "teacher_id='" + teacher_id + '\'' +
                ", course_id=" + course_id +
                '}';
    }
}
