package com.atguigu.book.controller;

import com.atguigu.book.pojo.User;
import com.atguigu.book.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

/**
 * ClassName: UserController
 * Package: com.atguigu.book.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 22:07
 * @Version 1.0
 */

public class UserController {
    private UserService userService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            session.setAttribute("currUser",user);
            return "redirect:book.do";
        }
        // 登录失败
        return "user/login";
    }
}
