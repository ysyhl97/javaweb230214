package com.atguigu.book.controller;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartService;
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
    private CartService cartService;

    public String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            // 获得当前用户的购物车
            Cart cart = cartService.getCart(user);
            // 将购物车封装到user中
            user.setCart(cart);

            session.setAttribute("currUser",user);
            // 登录成功,跳转到bookController的index()
            return "redirect:book.do";
        }
        // 登录失败
        return "user/login";
    }
}
