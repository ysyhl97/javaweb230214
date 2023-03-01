package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Filter01
 * Package: com.atguigu.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 11:02
 * @Version 1.0
 */
@WebFilter("*.do")
public class Filter01 implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      System.out.println("A");
      filterChain.doFilter(servletRequest,servletResponse);
      System.out.println("A");
   }

   @Override
   public void destroy() {

   }
}
