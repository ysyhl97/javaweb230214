package com.atguigu.myssm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ClassName: CharacterEncodingFilter
 * Package: com.atguigu.myssm.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 16:45
 * @Version 1.0
 */
@WebFilter("*.do")
public class CharacterEncodingFilter implements Filter {

    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 在filter设置post请求的编码
        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
