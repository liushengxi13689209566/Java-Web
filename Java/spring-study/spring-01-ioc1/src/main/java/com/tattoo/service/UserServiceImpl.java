package com.tattoo.service;

import com.tattoo.dao.UserDao;
import com.tattoo.dao.UserDaoImpl;
import com.tattoo.dao.UserDaoMysqlImpl;

public class UserServiceImpl implements UserService {

      /*
      private UserDao userDao = new UserDaoMysqlImpl();

      public void getUser() {
          userDao.getUser();
      }
      */

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
