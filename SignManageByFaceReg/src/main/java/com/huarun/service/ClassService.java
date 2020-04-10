package com.huarun.service;

import com.huarun.pojo.ClassInfo;
import org.apache.ibatis.annotations.Param;

public interface ClassService {
    //根据 classID 查询班级信息
    ClassInfo getClassInfoByClassID(@Param("class_id") int class_id);
}
