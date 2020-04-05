package com.huarun.dao;

import com.huarun.pojo.CourseTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CourseTimeMapper {
    //根据 courseID 获得课程表
    List<CourseTime> getCourseTimeByCourseID(@Param("course_id") int course_id);
}
