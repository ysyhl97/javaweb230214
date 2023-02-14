package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: ServletDemo03
 * Package: com.atguigu.servlet
 * <p>
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 9:40
 * @Version 1.0
 */
@WebServlet("/demo03")
public class ServletDemo03 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null) {
            System.out.println("你是第一次来啊");
        }else {
            System.out.println("session = " + session.getId());
        }
    }
}
