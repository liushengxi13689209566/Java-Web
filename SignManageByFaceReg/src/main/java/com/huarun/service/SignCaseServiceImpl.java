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
    public int updateSignCaseByUserIDAndCourseID(String student_id, int course_id, String sign_case_bitmap) {
        //update   成功是1，失败是0，错误是异常。一定要捕捉异常。不然项目就崩了。
        int ret = 0;
        try {
            ret = signCaseMapper.updateSignCaseByUserIDAndCourseID(student_id, course_id, sign_case_bitmap);
        } catch (PersistenceException e) {
            ret = -1;
        } finally {
        }
        return ret;
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

    @Override
    public int deleteOneCourse(int course_id) {
        int ret = 0;
        try {
            ret = signCaseMapper.deleteOneCourse(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }
}
