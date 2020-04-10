package com.huarun.service;

import com.huarun.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

public interface StudentService {

    //需要学生的所有信息
    StudentInfo getStudentInfoByStuID(@Param("userID") String userID);
}
