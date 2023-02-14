package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Package: com.atguigu.demo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 16:32
 * @Version 1.0
 */

@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      session.setAttribute("Session","SessionName");

//      req.getRequestDispatcher("demo04").forward(req,resp);
      resp.sendRedirect("demo04");
   }
}
