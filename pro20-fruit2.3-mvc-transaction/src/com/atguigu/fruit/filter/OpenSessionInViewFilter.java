package com.atguigu.fruit.filter;

import com.atguigu.myssm.transaction.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

/**
 * ClassName: OpenSessionInViewFilter
 * Package: com.atguigu.fruit.filter
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 13:53
 * @Version 1.0
 */
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            // 开启事务
            TransactionManager.beginTransaction();
            System.out.println("开启事务");
            filterChain.doFilter(servletRequest, servletResponse);
            // 提交事务
            TransactionManager.commit();
            System.out.println("提交事务");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 异常时回滚
                TransactionManager.rollback();
                System.out.println("rollback事务");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
