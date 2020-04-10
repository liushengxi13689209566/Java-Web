package com.huarun.service;

import com.huarun.pojo.CourseTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeacherService {
    List<CourseTeacher> getTeacherAllCourse(@Param("teacher_id") String teacher_id);
}
