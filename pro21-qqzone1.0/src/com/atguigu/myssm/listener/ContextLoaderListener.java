package com.atguigu.myssm.listener;

import com.atguigu.myssm.IOC.BeanFactory;
import com.atguigu.myssm.IOC.impl.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName: ContextLoaderListener
 * Package: com.atguigu.myssm.listener
 * Description:
 * 监听上下文启动(servletContext),在上下文启动时创建IOC容器,将其保存到application作用域中
 * dispatcherServlet再从application作用域中获取IOC容器
 *
 * @Author ysyhl97
 * @Create 2023/3/5 16:52
 * @Version 1.0
 */

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // 获取servletContext对象
        ServletContext application = servletContextEvent.getServletContext();
        // 获取上下文的初始参数(即从web.xml中获取contextConfigLocation)
        String parameter = application.getInitParameter("contextConfigLocation");
        // 创建beanFactory对象
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(parameter);
        // 将beanFactory对象,放到application作用域中,供dispatcherSerlvet使用
        application.setAttribute("beanFactory", beanFactory);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
