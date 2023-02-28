package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Enumeration;

/**
 * ClassName: Demo01Servlet
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/28 11:08
 * @Version 1.0
 */
@WebServlet(urlPatterns = "/demo01",initParams = {
        @WebInitParam(name = "hello1",value = "demo01-hello1"),
        @WebInitParam(name = "user",value = "username")
})
public class Demo01Servlet extends HttpServlet {

   @Override
   public void init() throws ServletException {
      ServletConfig servletConfig = getServletConfig();
      Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
      System.out.println("initParameterNames = " + initParameterNames);

      ServletContext servletContext = getServletContext();
      String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
      Object configLocation = servletContext.getAttribute("contextConfigLocation");
      System.out.println("contextConfigLocation = " + contextConfigLocation);
      System.out.println("configLocation = " + configLocation);

   }
}
