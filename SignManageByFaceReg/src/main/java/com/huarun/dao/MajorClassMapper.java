package com.huarun.dao;

import com.huarun.pojo.MajorClassDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorClassMapper {
    //得到 一个专业有几个班级
    List<MajorClassDO> getClassListByMajorID(@Param("major_id") int major_id);
}
