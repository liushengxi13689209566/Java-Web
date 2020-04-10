package com.huarun.dao;

import com.huarun.pojo.MajorInfo;
import org.apache.ibatis.annotations.Param;

public interface MajorMapper {
    //    根据major_id查询专业信息
    MajorInfo getMajorInfoByID(@Param("major_id") int major_id);
}
