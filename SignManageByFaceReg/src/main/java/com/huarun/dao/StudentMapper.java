package com.huarun.dao;

import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    UserInfo getUserInfoByStuID(@Param("userID") String userID);
}
