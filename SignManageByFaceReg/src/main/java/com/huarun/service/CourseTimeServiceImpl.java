package com.huarun.service;

import com.huarun.dao.CourseTimeMapper;
import com.huarun.pojo.CourseTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTimeServiceImpl implements CourseTimeService {
    @Autowired
    private CourseTimeMapper courseTimeMapper;

    @Override
    public List<CourseTime> getCourseTimeByCourseID(int course_id) {
        return courseTimeMapper.getCourseTimeByCourseID(course_id);
    }
}
