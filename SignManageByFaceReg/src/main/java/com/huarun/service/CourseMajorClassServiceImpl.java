package com.huarun.service;

import com.huarun.dao.CourseMajorClassMapper;
import com.huarun.pojo.CourseMajorClassDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMajorClassServiceImpl implements CourseMajorClassService {
    @Autowired
    private CourseMajorClassMapper courseMajorClassMapper;

    @Override
    public List<CourseMajorClassDO> getMajorClassInfoByCourseID(int course_id) {
        return courseMajorClassMapper.getMajorClassInfoByCourseID(course_id);
    }
}
