package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ServletDemo02
 * Package: com.atguigu.servlet
 * Description:
 *  演示servlet的生命周期
 * @Author ysyhl97
 * @Create 2023/2/1 17:26
 * @Version 1.0
 */

public class ServletDemo02 extends HttpServlet {
    public ServletDemo02() {
        System.out.println("Servlet的实例化方法....");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("这是Servlet初始化方法......");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("这是Servlet的service方法....");
    }

    @Override
    public void destroy() {
        System.out.println("这是Servlet的destory方法....");
    }
}
