package com.huarun.service;

import com.huarun.dao.StudentMapper;
import com.huarun.pojo.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentInfo getStudentInfoByStuID(String userID) {
        return studentMapper.getStudentInfoByStuID(userID);
    }
}
