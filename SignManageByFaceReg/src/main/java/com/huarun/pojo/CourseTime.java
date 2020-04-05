package com.huarun.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseTime implements Serializable {
    private int id;
    private int course_id;
    private Timestamp course_start_timestamp;
    private Timestamp course_end_timestamp;

    public CourseTime(int id, int course_id, Timestamp course_start_timestamp, Timestamp course_end_timestamp) {
        this.id = id;
        this.course_id = course_id;
        this.course_start_timestamp = course_start_timestamp;
        this.course_end_timestamp = course_end_timestamp;
    }

    public CourseTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Timestamp getCourse_start_timestamp() {
        return course_start_timestamp;
    }

    public void setCourse_start_timestamp(Timestamp course_start_timestamp) {
        this.course_start_timestamp = course_start_timestamp;
    }

    public Timestamp getCourse_end_timestamp() {
        return course_end_timestamp;
    }

    public void setCourse_end_timestamp(Timestamp course_end_timestamp) {
        this.course_end_timestamp = course_end_timestamp;
    }

    @Override
    public String toString() {
        return "CourseTime{" +
                "id=" + id +
                ", course_id=" + course_id +
                ", course_start_timestamp=" + course_start_timestamp +
                ", course_end_timestamp=" + course_end_timestamp +
                '}';
    }
}
