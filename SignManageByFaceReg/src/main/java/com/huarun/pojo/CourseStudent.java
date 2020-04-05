package com.huarun.pojo;

import java.io.Serializable;

public class CourseStudent implements Serializable {
    private String student_id;
    private int course_id;

    public CourseStudent(String student_id, int course_id) {
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public CourseStudent() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "CourseStudent{" +
                "student_id='" + student_id + '\'' +
                ", course_id=" + course_id +
                '}';
    }
}
