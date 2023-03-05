package com.atguigu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: MyServletContextListener
 * Package: com.atguigu.listener
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/2 10:31
 * @Version 1.0
 */

//@WebListener
public class MyServletContextListener implements ServletContextListener {
   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      System.out.println("servletContext上下文初始化时监听.........");
   }

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      System.out.println("servletContext上下文销毁时监听到了.......");
   }
}
