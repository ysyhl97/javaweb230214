package com.atguigu.fruit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ClassName: CharacterEncodingFilter
 * Package: com.atguigu.fruit.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 11:23
 * @Version 1.0
 */
@WebFilter("*.do")
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest) servletRequest).setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
