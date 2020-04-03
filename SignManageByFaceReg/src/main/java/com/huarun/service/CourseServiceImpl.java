package com.huarun.service;

import com.huarun.dao.CourseMapper;
import com.huarun.pojo.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseInfo> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }
}
