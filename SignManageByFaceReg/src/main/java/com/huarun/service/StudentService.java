package com.huarun.service;

import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    //需要学生的所有信息
    StudentInfo getStudentInfoByStuID(@Param("userID") String userID) throws StudentServiceException;

    //查询所有学生的所有信息
    List<StudentInfo> getAllStudentsInfo() throws StudentServiceException;

}
