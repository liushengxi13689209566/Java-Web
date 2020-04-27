package com.huarun.service;

import com.huarun.pojo.CourseTime;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;


public interface CourseTimeService {
    //根据 courseID 获得课程表
    List<CourseTime> getCourseTimeByCourseID(int course_id);


    //根据 courseID time 获得在该时间节点之前的所有课程表
    List<CourseTime> getCourseTimeByTime(int course_id, String interval_time);
}
