package com.huarun.service;

import com.huarun.dao.StudentMapper;
import com.huarun.exception.StudentServiceException;
import com.huarun.pojo.StudentDO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentDO getStudentInfoByStuID(String userID) throws StudentServiceException {
        try {
            return studentMapper.getStudentInfoByStuID(userID);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public List<StudentDO> getStudentInfoByMajorIDAndClassID(int major_id, int class_id) throws StudentServiceException {
        try {
            return studentMapper.getStudentInfoByMajorIDAndClassID(major_id, class_id);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public List<StudentDO> getAllStudentsInfo() throws StudentServiceException {
        try {
            return studentMapper.getAllStudentsInfo();
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public String getMaxStuID() throws StudentServiceException {
        try {
            return studentMapper.getMaxStuID();
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }

    @Override
    public void addOneStudent(StudentDO studentDO) throws StudentServiceException {
        try {
            System.out.println("studentDO == " + studentDO);
            studentMapper.addOneStudent(studentDO);
        } catch (PersistenceException e) {
            throw new StudentServiceException(e);
        }
    }
}
