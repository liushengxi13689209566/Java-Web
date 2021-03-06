package com.huarun.service;

import com.huarun.exception.SignCaseServiceException;
import com.huarun.pojo.SignCase;
import com.huarun.pojo.StudentDO;
import org.apache.ibatis.annotations.Param;

public interface SignCaseService {
    //根据 userID + courseID 获取考勤数据
    SignCase getSignCaseByUserIDAndCourseID(@Param("student_id") String student_id,
                                            @Param("course_id") int course_id);

    SignCase getSignCaseByUserDOAndCourseID(@Param("studentDo") StudentDO studentDo,
                                            @Param("course_id") int course_id);

    //刷新考勤记录
    int updateSignCaseByUserIDAndCourseID(@Param("student_id") String student_id,
                                          @Param("course_id") int course_id,
                                          @Param("sign_case_bitmap") String sign_case_bitmap);

    //初始化一个学生一门课的考勤记录
    int initSignCaseOneStudentOneCourse(@Param("student_id") String student_id,
                                        @Param("course_id") int course_id) throws SignCaseServiceException;

    //删除一个学生一门课的考勤记录
    int deleteSignCaseOneStudentOneCourse(@Param("student_id") String student_id,
                                          @Param("course_id") int course_id) throws SignCaseServiceException;

    //彻底删除一门课
    int deleteOneCourse(@Param("course_id") int course_id);

    //删除一个学生
    int deleteOneStudent(@Param("userID") String userID);
}
