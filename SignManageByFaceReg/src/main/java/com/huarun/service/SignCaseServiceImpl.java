package com.huarun.service;

import com.huarun.dao.SignCaseMapper;
import com.huarun.exception.CourseStudentServiceException;
import com.huarun.exception.SignCaseServiceException;
import com.huarun.pojo.SignCase;
import com.huarun.pojo.StudentDO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignCaseServiceImpl implements SignCaseService {
    @Autowired
    private SignCaseMapper signCaseMapper;

    @Override
    public SignCase getSignCaseByUserIDAndCourseID(String student_id, int course_id) {
        return signCaseMapper.getSignCaseByUserIDAndCourseID(student_id, course_id);
    }

    @Override
    public SignCase getSignCaseByUserDOAndCourseID(StudentDO studentDo, int course_id) {
        return signCaseMapper.getSignCaseByUserIDAndCourseID(studentDo.getId(), course_id);
    }

    @Override
    public int setgetSignCaseByUserIDAndCourseID(String student_id, int course_id, String sign_case_bitmap) {
        return signCaseMapper.setgetSignCaseByUserIDAndCourseID(student_id, course_id, sign_case_bitmap);
    }

    @Override
    public int initSignCaseOneStudentOneCourse(String student_id, int course_id) throws SignCaseServiceException {
        try {
            return signCaseMapper.initSignCaseOneStudentOneCourse(student_id, course_id);
        } catch (PersistenceException e) {
            throw new SignCaseServiceException(e);
        }
    }

    @Override
    public int deleteSignCaseOneStudentOneCourse(String student_id, int course_id) throws SignCaseServiceException {
        try {
            return signCaseMapper.deleteSignCaseOneStudentOneCourse(student_id, course_id);
        } catch (PersistenceException e) {
            throw new SignCaseServiceException(e);
        }
    }
}
