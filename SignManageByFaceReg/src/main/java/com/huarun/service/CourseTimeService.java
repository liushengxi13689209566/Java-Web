package com.huarun.service;

import com.huarun.pojo.CourseTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CourseTimeService {
    //根据 courseID 获得课程表
    List<CourseTime> getCourseTimeByCourseID(@Param("course_id") int course_id);
}
