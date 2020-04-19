package com.huarun.service;

import com.huarun.pojo.MajorDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorService {
    //    根据major_id 查询专业信息
    MajorDO getMajorInfoByID(@Param("major_id") int major_id);

    //获得所有专业
    List<MajorDO> getAllMajor();
}
