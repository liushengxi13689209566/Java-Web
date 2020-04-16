package com.huarun.service;

import com.huarun.dao.StudentMapper;
import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentInfo getStudentInfoByStuID(String userID) throws StudentServiceException {
        try {
            return studentMapper.getStudentInfoByStuID(userID);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public List<StudentInfo> getAllStudentsInfo() throws StudentServiceException {
        try {
            return studentMapper.getAllStudentsInfo();
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }
}
