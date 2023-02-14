package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ServletDemo01
 * Package: com.atguigu.servlet
 * Description:
 * 演示只重写doPost(),接受get请求
 *
 * @Author ysyhl97
 * @Create 2023/2/1 17:22
 * @Version 1.0
 */

public class ServletDemo01 extends HttpServlet {

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doPost(req, resp);
   }
}
