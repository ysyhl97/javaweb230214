package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.atguigu.demo
 * Description:
 *    演示request域的作用范围(在转发和重定向下)
 * @Author ysyhl97
 * @Create 2023/2/2 16:06
 * @Version 1.0
 */
@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      给request域中,存入数据
      req.setAttribute("name","Judy");
//      服务端的请求转发
//      req.getRequestDispatcher("demo02").forward(req,resp);

//      客户端请求重定向
//      resp.sendRedirect("demo02");
   }
}
