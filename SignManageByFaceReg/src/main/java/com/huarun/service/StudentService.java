package com.huarun.service;

import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService {

    //需要学生的所有信息
    StudentDO getStudentInfoByStuID(@Param("userID") String userID) throws StudentServiceException;

    //查询所有学生的所有信息
    List<StudentDO> getAllStudentsInfo() throws StudentServiceException;

    //得到最大的学号
    String getMaxStuID() throws StudentServiceException;

    //添加一个学生，密码默认为：身份证号后六位
    void addOneStudent(@Param("studentDO") StudentDO studentDO) throws StudentServiceException;
}
