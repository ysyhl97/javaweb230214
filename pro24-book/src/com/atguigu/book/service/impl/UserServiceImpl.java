package com.atguigu.book.service.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Package: com.atguigu.book.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 22:06
 * @Version 1.0
 */

public class UserServiceImpl implements UserService {

   private UserDao userDao;
   @Override
   public User login(String uname, String pwd) {
      return userDao.selectUser(uname,pwd);
   }
}
