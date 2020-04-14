package com.huarun.dao;

import com.huarun.pojo.StudentInfo;
import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    //只要 name,password
    UserInfo getUserInfoByStuID(@Param("userID") String userID);

    //需要学生的所有信息
    StudentInfo getStudentInfoByStuID(@Param("userID") String userID);
}
