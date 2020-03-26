package com.huarun.dao;

import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    UserInfo getUserInfoByTeaID(@Param("userID") String userID);
}
