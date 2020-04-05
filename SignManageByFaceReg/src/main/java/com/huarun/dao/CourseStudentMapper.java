package com.huarun.dao;

import com.huarun.pojo.CourseStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseStudentMapper {
    //    根据用户ID 找到 课程ID
    List<CourseStudent> queryMyCourseIDByUserID(@Param("userID") String userID);
}
