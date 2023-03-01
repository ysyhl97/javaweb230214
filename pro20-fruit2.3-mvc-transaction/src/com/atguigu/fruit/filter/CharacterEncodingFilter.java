package com.atguigu.fruit.filter;

import com.atguigu.myssm.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
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
@WebFilter(urlPatterns = "*.do", initParams = @WebInitParam(name = "encoding", value = "utf-8"))
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (StringUtil.isNotEmpty(encodingStr)) {
            encoding = encodingStr;
        } else {
            encoding = "utf-8";
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ((HttpServletRequest) servletRequest).setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}
