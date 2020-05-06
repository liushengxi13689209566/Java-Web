package com.huarun.dao;

import com.huarun.pojo.CourseTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTeacherMapper {
    List<CourseTeacher> getTeacherAllCourse(@Param("teacher_id") String teacher_id);

    //添加一门课
    int addOneCourseToTeaID(@Param("teacher_id") String teacher_id, @Param("course_id") int course_id);

    //删除一门课
    int deleteOnCourseInTeaID(@Param("teacher_id") String teacher_id, @Param("course_id") int course_id);
}
