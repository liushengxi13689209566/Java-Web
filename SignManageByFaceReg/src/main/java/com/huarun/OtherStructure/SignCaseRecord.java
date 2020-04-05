package com.huarun.OtherStructure;

import java.sql.Timestamp;

public class SignCaseRecord {
    //那一天的那一节课，签到标志位
    private Timestamp course_start_timestamp;
    private Timestamp course_end_timestamp;
    private char sign_case_flag;

    public SignCaseRecord(Timestamp course_start_timestamp, Timestamp course_end_timestamp, char sign_case_flag) {
        this.course_start_timestamp = course_start_timestamp;
        this.course_end_timestamp = course_end_timestamp;
        this.sign_case_flag = sign_case_flag;
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

    public char getSign_case_flag() {
        return sign_case_flag;
    }

    public void setSign_case_flag(char sign_case_flag) {
        this.sign_case_flag = sign_case_flag;
    }

    @Override
    public String toString() {
        return "SignCaseRecord{" +
                "course_start_timestamp=" + course_start_timestamp +
                ", course_end_timestamp=" + course_end_timestamp +
                ", sign_case_flag=" + sign_case_flag +
                '}';
    }
}
