package com.huarun.service;

import com.huarun.pojo.CourseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    //查询全部的课程
    List<CourseInfo> queryAllCourse();


    //根据 主键ID 查询课程
    CourseInfo queryCourseByID(@Param("id") int id);

}
