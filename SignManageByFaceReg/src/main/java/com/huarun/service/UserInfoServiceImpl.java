package com.huarun.service;

import com.huarun.dao.AdminMapper;
import com.huarun.dao.StudentMapper;
import com.huarun.dao.TeacherMapper;
import com.huarun.pojo.StudentInfo;
import com.huarun.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户账户信息 service 实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserInfo getUserInfoByStuID(String userID) {
        return studentMapper.getUserInfoByStuID(userID);
    }

    @Override
    public UserInfo getUserInfoByTeaID(String userID) {
        return teacherMapper.getUserInfoByTeaID(userID);
    }

    @Override
    public UserInfo getUserInfoByAdmID(String userID) {
        return adminMapper.getUserInfoByAdmID(userID);
    }
}
