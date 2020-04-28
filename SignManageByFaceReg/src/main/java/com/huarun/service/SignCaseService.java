package com.huarun.service;

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
    void setgetSignCaseByUserIDAndCourseID(@Param("student_id") String student_id,
                                           @Param("course_id") int course_id,
                                           @Param("sign_case_bitmap") String sign_case_bitmap);
}
