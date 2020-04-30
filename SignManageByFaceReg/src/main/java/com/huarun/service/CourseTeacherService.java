package com.huarun.service;

import com.huarun.pojo.CourseTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeacherService {
    //得到老师教的所有课程
    List<CourseTeacher> getTeacherAllCourse(@Param("teacher_id") String teacher_id);

    //添加一门课
    int addOneCourseToTeaID(@Param("teacher_id") String teacher_id, @Param("course_id") int course_id);
}
