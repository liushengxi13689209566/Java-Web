package com.huarun.service;

import com.huarun.dao.CourseTimeMapper;
import com.huarun.pojo.CourseTime;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    public int getTotalCourseCountBeforeToday(int course_id) {
        int ret = 0;
        try {
            ret = courseTimeMapper.getTotalCourseCountBeforeToday(course_id);
        } catch (PersistenceException e) {
            ret = -1;
        } finally {
        }
        return ret;
    }

    @Override
    public List<CourseTime> getCourseTimeByTime(int course_id, String interval_time) {
        return courseTimeMapper.getCourseTimeByTime(course_id, interval_time);
    }

    @Override
    public CourseTime getSignCourseTime(int course_id, long sign_timestamp) {
        CourseTime courseTime = new CourseTime();
        try {
            courseTime = courseTimeMapper.getSignCourseTime(course_id, sign_timestamp / 1000);//单位需要转化为秒
        } catch (PersistenceException e) {
            courseTime = null;
        } finally {
        }
        return courseTime;
    }

    @Override
    public Map<String, Object> importOneCourseTime(MultipartFile file, int course_id) {
        return null;
    }
}
