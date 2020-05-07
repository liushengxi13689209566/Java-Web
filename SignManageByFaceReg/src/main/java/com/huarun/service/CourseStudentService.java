package com.huarun.service;

import com.huarun.exception.CourseStudentServiceException;
import com.huarun.pojo.CourseStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CourseStudentService {
    //    根据用户ID 找到所学课程ID：找到每一个学生所上的课程
    List<CourseStudent> queryMyCourseIDByUserID(@Param("userID") String userID);

    //根据课程ID 找到一门课所有的学生
    List<CourseStudent> queryOneCourseAllStudent(@Param("course_id") int course_id);

    //删除一门课中的一个学生
    int delOneStudentInCourse(@Param("course_id") int course_id, @Param("student_id") String student_id) throws CourseStudentServiceException;
    
    //删除一门课
    int deleteOneCourse(@Param("course_id") int course_id);

    //添加一个学生到一门课中
    int addOneStudentInCourse(@Param("course_id") int course_id, @Param("student_id") String student_id) throws CourseStudentServiceException;

    /**
     * 从文件中导入学生信息到一门课程中
     *
     * @param file 导入信息的文件
     * @return 返回一个Map，其中：key为total代表导入的总记录数，key为available代表有效导入的记录数
     */
    Map<String, Object> importOneCourseStudents(MultipartFile file, int course_id) throws CourseStudentServiceException, IOException;

}
