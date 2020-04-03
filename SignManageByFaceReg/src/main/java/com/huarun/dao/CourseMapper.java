package com.huarun.dao;


import com.huarun.pojo.CourseInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    //查询全部的课程
    List<CourseInfo> queryAllCourse();
}
