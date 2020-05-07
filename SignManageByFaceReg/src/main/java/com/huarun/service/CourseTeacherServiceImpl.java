package com.huarun.service;

import com.huarun.dao.CourseTeacherMapper;
import com.huarun.pojo.CourseTeacher;
import org.apache.ibatis.exceptions.PersistenceException;
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

    @Override
    public int addOneCourseToTeaID(String teacher_id, int course_id) {
        int ret = 0;
        try {
            ret = courseTeacherMapper.addOneCourseToTeaID(teacher_id, course_id);
        } catch (PersistenceException e) {
            ret = -1;
        } finally {
        }
        return ret;
    }

    @Override
    public int deleteOnCourseInTeaID(String teacher_id, int course_id) {
        int ret = 0;
        try {
            ret = courseTeacherMapper.deleteOnCourseInTeaID(teacher_id, course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }

    @Override
    public int deleteOneCourse(int course_id) {
        int ret = 0;
        try {
            ret = courseTeacherMapper.deleteOneCourse(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }
}
