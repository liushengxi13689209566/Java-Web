package com.huarun.dao;


import com.huarun.pojo.CourseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    //查询全部的课程
    List<CourseInfo> queryAllCourse();

    //根据 主键ID 查询课程
    CourseInfo queryCourseByID(@Param("id") int id);

}
