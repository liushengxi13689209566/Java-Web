package com.huarun.service;

import com.huarun.pojo.SignCase;
import org.apache.ibatis.annotations.Param;

public interface SignCaseService {
    //根据 userID + courseID 获取考勤数据
    SignCase getSignCaseByUserIDAndCourseID(@Param("student_id") String student_id,
                                            @Param("course_id") int course_id);
}
