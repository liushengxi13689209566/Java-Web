package com.tattoo.service;

import com.tattoo.dao.UserDaoMysqlImpl;

public class MyTest {
    public static void main(String[] args) {

         UserServiceImpl userService = new UserServiceImpl();

         userService.setUserDao(new UserDaoMysqlImpl());

         userService.getUser();

    }
}
