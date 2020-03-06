package com.tattoo.dao;

public class UserDaoMysqlImpl implements UserDao {
    //重写方法
    public void getUser() {
        System.out.println("使用 Mysql 获取用户");
    }
}
