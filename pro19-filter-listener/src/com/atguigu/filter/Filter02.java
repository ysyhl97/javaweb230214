package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName: Filter02
 * Package: com.atguigu.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 11:04
 * @Version 1.0
 */
@WebFilter("*.do")
public class Filter02 implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      System.out.println("B");
      filterChain.doFilter(servletRequest,servletResponse);
      System.out.println("B");
   }

   @Override
   public void destroy() {

   }
}
