package com.huarun.dao;

import com.huarun.pojo.StudentInfo;
import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    //只要 name,password
    UserInfo getUserInfoByStuID(@Param("userID") String userID);

    //需要学生的所有信息
    StudentInfo getStudentInfoByStuID(@Param("userID") String userID);

    //查询所有学生的所有信息
    List<StudentInfo> getAllStudentsInfo();
}
