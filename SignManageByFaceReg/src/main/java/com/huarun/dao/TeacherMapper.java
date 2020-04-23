package com.huarun.dao;

import com.huarun.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    UserInfo getUserInfoByTeaID(@Param("userID") String userID);

    void teaPasswordModify(@Param("userID") String userID, @Param("new_pass") String new_pass);

}
