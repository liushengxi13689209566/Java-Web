package com.huarun.dao;

import com.huarun.pojo.CourseStudent;
import com.huarun.test.StuIDpojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseStudentMapper {
    //    根据用户ID 找到所学课程ID：找到每一个学生所上的课程
    List<CourseStudent> queryMyCourseIDByUserID(@Param("userID") String userID);

    //根据课程ID 找到一门课所有的学生
    List<CourseStudent> queryOneCourseAllStudent(@Param("course_id") int course_id);

    //删除一门课中的一个学生
    int delOneStudentInCourse(@Param("course_id") int course_id, @Param("student_id") String student_id);

    //删除一门课
    int deleteOneCourse(@Param("course_id") int course_id);

    //删除一个学生
    int deleteOneStudent(@Param("userID") String userID);

    //添加一个学生到一门课中
    int addOneStudentInCourse(@Param("course_id") int course_id, @Param("student_id") String student_id);

    //批量导入一门课学生
    void insertBatch(@Param("params") Map params);

}
