package com.huarun.service;

import com.huarun.exception.CourseStudentServiceException;
import com.huarun.pojo.CourseTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public interface CourseTimeService {
    //根据 courseID 获得课程表
    List<CourseTime> getCourseTimeByCourseID(int course_id);

    //根据 courseID 获得课程表总的数目
    int getTotalCountByCourseID(int course_id);

    //得到之前总共有多少课程
    int getTotalCourseCountBeforeToday(int course_id);

    //根据 courseID time 获得在该时间节点之前的所有课程表
    List<CourseTime> getCourseTimeByTime(int course_id, String interval_time);


    //获得考勤的时间表，only 一条数据
    CourseTime getSignCourseTime(int course_id, long sign_timestamp);

    int deleteOneCourse(int course_id);


    Map<String, Object> importOneCourseTime(MultipartFile file, int course_id);
}
