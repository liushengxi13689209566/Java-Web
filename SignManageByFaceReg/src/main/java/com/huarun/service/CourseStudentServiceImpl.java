package com.huarun.service;

import com.huarun.dao.CourseStudentMapper;
import com.huarun.pojo.CourseStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseStudentServiceImpl implements CourseStudentService {
    @Autowired
    private CourseStudentMapper courseStudentMapper;

    @Override
    public List<CourseStudent> queryMyCourseIDByUserID(String userID) {
        return courseStudentMapper.queryMyCourseIDByUserID(userID);
    }
}
