package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo04Servlet
 * Package: com.atguigu.demo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 16:34
 * @Version 1.0
 */

@WebServlet("/demo04")
public class Demo04Servlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Object session = req.getSession().getAttribute("Session");
      System.out.println("session = " + session);
   }
}
