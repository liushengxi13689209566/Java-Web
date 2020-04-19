package com.huarun.service;

import com.huarun.pojo.ClassDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassService {
    //根据 classID 查询班级信息
    ClassDO getClassInfoByClassID(@Param("class_id") int class_id);

    //获得所有班级
    List<ClassDO> getAllMajor();
}
