package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ServletDemo06
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 10:28
 * @Version 1.0
 */
@WebServlet("/demo06")
public class ServletDemo06 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("当前是demo06");
        // 服务器的请求转发
//      req.getRequestDispatcher("demo07").forward(req,resp);

//      客户端重定向
        resp.sendRedirect("demo07");
    }
}
