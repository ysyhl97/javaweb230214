package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Demo01Filter
 * Package: com.atguigu.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 10:44
 * @Version 1.0
 */

@WebFilter("/demo01.do")
public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       System.out.println("helloA..");
       filterChain.doFilter(servletRequest,servletResponse);
       System.out.println("helloB..");
    }

    @Override
    public void destroy() {

    }
}
