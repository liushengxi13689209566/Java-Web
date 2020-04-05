package com.huarun.service;

import com.huarun.dao.SignCaseMapper;
import com.huarun.pojo.SignCase;
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
}
