package com.huarun.service;

import com.huarun.pojo.CourseInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    //查询全部的书
    List<CourseInfo> queryAllCourse();
}
