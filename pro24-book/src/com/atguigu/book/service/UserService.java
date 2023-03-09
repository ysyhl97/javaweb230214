package com.atguigu.book.service;

import com.atguigu.book.pojo.User;

/**
 * ClassName: UserService
 * Package: com.atguigu.book.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 22:05
 * @Version 1.0
 */

public interface UserService {

   User login(String uname, String pwd);
}
