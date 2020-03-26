package com.huarun.dao;

import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    UserInfo getUserInfoByAdmID(@Param("userID") String userID);
}