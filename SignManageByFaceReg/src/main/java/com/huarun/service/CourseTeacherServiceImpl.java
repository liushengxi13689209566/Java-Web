package com.huarun.service;

import com.huarun.dao.CourseTeacherMapper;
import com.huarun.pojo.CourseTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
    @Autowired
    private CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> getTeacherAllCourse(String teacher_id) {
        return courseTeacherMapper.getTeacherAllCourse(teacher_id);
    }
}
