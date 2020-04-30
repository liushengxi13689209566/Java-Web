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


    //根据 courseID time 获得在该时间节点之前的所有课程表
    List<CourseTime> getCourseTimeByTime(int course_id, String interval_time);

    Map<String, Object> importOneCourseTime(MultipartFile file, int course_id);
}
