package com.huarun.service;

import com.huarun.dao.CourseMapper;
import com.huarun.pojo.CourseDO;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<CourseDO> queryAllCourse() {
        return courseMapper.queryAllCourse();
    }

    @Override
    public CourseDO queryCourseByID(int id) {
        return courseMapper.queryCourseByID(id);
    }

    @Override
    public int addOneCourse(CourseDO courseDo) {
        int ret = 0;
        try {
            ret = courseMapper.addOneCourse(courseDo);
        } catch (PersistenceException e) {
            ret = -1;
        } finally {
        }

        System.out.println("ret == " + ret);

        return ret;
    }

    @Override
    public int deleteOneCourse(int course_id) {
        int ret = 0;
        try {
            ret = courseMapper.deleteOneCourse(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        }
        return ret;
    }
}
