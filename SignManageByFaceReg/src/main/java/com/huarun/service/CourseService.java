package com.huarun.service;

import com.huarun.pojo.CourseDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CourseService {
    //查询全部的课程
    List<CourseDO> queryAllCourse();


    //根据 主键ID 查询课程
    CourseDO queryCourseByID(@Param("id") int id);

    //添加一门课程信息，返回对应的 course_id
    int addOneCourse(@Param("courseDo") CourseDO courseDo);
}
