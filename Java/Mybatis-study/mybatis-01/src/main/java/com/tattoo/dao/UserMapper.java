package com.tattoo.dao;

import com.tattoo.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    //根据 id 查询用户
    User getUserById(int id);
}
