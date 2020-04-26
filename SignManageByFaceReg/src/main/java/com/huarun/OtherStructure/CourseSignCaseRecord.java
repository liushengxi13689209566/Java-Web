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

    private Timestamp course_start_timestamp; //课程时间，只有年月日

    private int late_count; //迟到人数
    private int truancy_count; //旷课人数
    private int success_count; //出勤人数

}
