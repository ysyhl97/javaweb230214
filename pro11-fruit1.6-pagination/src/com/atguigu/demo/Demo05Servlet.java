package com.atguigu.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo05Servlet
 * Package: com.atguigu.demo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 16:47
 * @Version 1.0
 */

@WebServlet("/demo06")
public class Demo05Servlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ServletContext application = req.getServletContext();
      application.setAttribute("applicationName","applicationName");

      req.getRequestDispatcher("demo07").forward(req,resp);
   }
}
