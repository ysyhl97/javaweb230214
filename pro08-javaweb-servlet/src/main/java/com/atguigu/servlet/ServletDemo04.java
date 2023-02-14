package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: ServletDemo04
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 10:02
 * @Version 1.0
 */

@WebServlet("/demo04")
public class ServletDemo04 extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      session.setAttribute("name","Judy");
   }
}
