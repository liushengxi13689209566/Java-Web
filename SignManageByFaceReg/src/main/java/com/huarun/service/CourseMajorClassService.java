package com.huarun.service;

import com.huarun.pojo.CourseMajorClassDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMajorClassService {
    //得到所有上这门课的班级和专业的信息
    List<CourseMajorClassDO> getMajorClassInfoByCourseID(@Param("course_id") int course_id);
}
