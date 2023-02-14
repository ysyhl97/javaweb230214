package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo07Servlet
 * Package: com.atguigu.demo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 16:49
 * @Version 1.0
 */
@WebServlet("/demo07")
public class Demo07Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object applicationName = req.getServletContext().getAttribute("applicationName");
        System.out.println("applicationName = " + applicationName);
    }
}
