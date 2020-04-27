package com.huarun.dao;

import com.huarun.pojo.CourseTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


public interface CourseTimeMapper {
    //根据 courseID 获得课程表
    List<CourseTime> getCourseTimeByCourseID(@Param("course_id") int course_id);

    //根据 courseID  interval 获得在当前时间节点之前间隔 interval 的所有课程表信息
    List<CourseTime> getCourseTimeByTime(@Param("course_id") int course_id, @Param("interval_time") String interval_time);
}
