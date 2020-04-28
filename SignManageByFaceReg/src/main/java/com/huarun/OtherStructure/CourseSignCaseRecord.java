package com.huarun.OtherStructure;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseSignCaseRecord implements Serializable {

    private int id; //编号
    private int major_id;
    private String major_name;

    private int class_id;
    private String class_name;

    private int course_id;
    private String course_name; //与本老师相关的所有课程

    private int bitmap_idx;//该日期对应下的位图下标
    private Timestamp course_start_timestamp; //课程时间，只有年月日

    private int late_count; //迟到人数
    private int truancy_count; //旷课人数
    private int success_count; //出勤人数

    public CourseSignCaseRecord(int id, int major_id, String major_name, int class_id, String class_name, int course_id, String course_name, int bitmap_idx, Timestamp course_start_timestamp, int late_count, int truancy_count, int success_count) {
        this.id = id;
        this.major_id = major_id;
        this.major_name = major_name;
        this.class_id = class_id;
        this.class_name = class_name;
        this.course_id = course_id;
        this.course_name = course_name;
        this.bitmap_idx = bitmap_idx;
        this.course_start_timestamp = course_start_timestamp;
        this.late_count = late_count;
        this.truancy_count = truancy_count;
        this.success_count = success_count;
    }

    public CourseSignCaseRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBitmap_idx() {
        return bitmap_idx;
    }

    public void setBitmap_idx(int bitmap_idx) {
        this.bitmap_idx = bitmap_idx;
    }

    public Timestamp getCourse_start_timestamp() {
        return course_start_timestamp;
    }

    public void setCourse_start_timestamp(Timestamp course_start_timestamp) {
        this.course_start_timestamp = course_start_timestamp;
    }

    public int getLate_count() {
        return late_count;
    }

    public void setLate_count(int late_count) {
        this.late_count = late_count;
    }

    public int getTruancy_count() {
        return truancy_count;
    }

    public void setTruancy_count(int truancy_count) {
        this.truancy_count = truancy_count;
    }

    public int getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(int success_count) {
        this.success_count = success_count;
    }

    @Override
    public String toString() {
        return "CourseSignCaseRecord{" +
                "id=" + id +
                ", major_id=" + major_id +
                ", major_name='" + major_name + '\'' +
                ", class_id=" + class_id +
                ", class_name='" + class_name + '\'' +
                ", course_id=" + course_id +
                ", course_name='" + course_name + '\'' +
                ", bitmap_idx=" + bitmap_idx +
                ", course_start_timestamp=" + course_start_timestamp +
                ", late_count=" + late_count +
                ", truancy_count=" + truancy_count +
                ", success_count=" + success_count +
                '}';
    }
}
