package com.huarun.pojo;

import java.io.Serializable;
import java.util.Date;

public class CourseDO implements Serializable {
    private int course_id;
    private String course_name;
    private int course_times;
    private int course_credit;
    private Date course_start;
    private Date course_end;

    public CourseDO(int course_id, String course_name, int course_times, int course_credit, Date course_start, Date course_end) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_times = course_times;
        this.course_credit = course_credit;
        this.course_start = course_start;
        this.course_end = course_end;
    }

    public CourseDO() {
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

    public int getCourse_times() {
        return course_times;
    }

    public void setCourse_times(int course_times) {
        this.course_times = course_times;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(int course_credit) {
        this.course_credit = course_credit;
    }

    public Date getCourse_start() {
        return course_start;
    }

    public void setCourse_start(Date course_start) {
        this.course_start = course_start;
    }

    public Date getCourse_end() {
        return course_end;
    }

    public void setCourse_end(Date course_end) {
        this.course_end = course_end;
    }

    @Override
    public String toString() {
        return "CourseDO{" +
                "course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", course_times=" + course_times +
                ", course_credit=" + course_credit +
                ", course_start=" + course_start +
                ", course_end=" + course_end +
                '}';
    }
}
