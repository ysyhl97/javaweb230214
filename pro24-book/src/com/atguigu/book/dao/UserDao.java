package com.atguigu.book.dao;

import com.atguigu.book.pojo.User;

/**
 * ClassName: UserDao
 * Package: com.atguigu.book.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:48
 * @Version 1.0
 */

public interface UserDao {
   User selectUser(String uname, String pwd);
}
