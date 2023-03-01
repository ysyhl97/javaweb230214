package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 10:29
 * @Version 1.0
 */
@WebServlet("/demo03.do")
public class Demo03Servlet extends HttpServlet {
   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      System.out.println("demo03Servlet service........");
      req.getRequestDispatcher("succ.html").forward(req,resp);
   }
}
