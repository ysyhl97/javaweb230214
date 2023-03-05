package com.atguigu.myssm.listener;

import com.atguigu.myssm.io.BeanFactory;
import com.atguigu.myssm.io.impl.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ContextLoaderListener
 * Package: com.atguigu.myssm.listener
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/2 10:47
 * @Version 1.0
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application = servletContextEvent.getServletContext();
        String path = application.getInitParameter("contextConfigLocation");
        //通过监听器,监听到servletContext启动时,同时创建IOC容器
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        //将beanFactory放入到servletContext作用域中
        application.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
