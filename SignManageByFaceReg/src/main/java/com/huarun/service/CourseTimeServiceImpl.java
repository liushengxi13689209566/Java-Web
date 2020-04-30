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

    @Override
    public int getTotalCountByCourseID(int course_id) {
        return courseTimeMapper.getTotalCountByCourseID(course_id);
    }

    @Override
    public List<CourseTime> getCourseTimeByTime(int course_id, String interval_time) {
        return courseTimeMapper.getCourseTimeByTime(course_id, interval_time);
    }
}
