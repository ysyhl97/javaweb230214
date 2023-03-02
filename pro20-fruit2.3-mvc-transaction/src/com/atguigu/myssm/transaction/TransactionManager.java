package com.atguigu.myssm.transaction;

import com.atguigu.myssm.base.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: TransactionManager
 * Package: com.atguigu.myssm.transaction
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 13:55
 * @Version 1.0
 */

public class TransactionManager {

    // 事务开始
    public static void beginTransaction() throws SQLException {
//        Connection connection = threadLocal.get();
        /**
         if (connection != null) {
         connection.setAutoCommit(false);
         } else {
         connection = ConnectionUtil.getConn();
         connection.setAutoCommit(false);
         threadLocal.set(connection);
         }*/

        // 对上述逻辑的优化
//        if (connection == null) {
//            connection = ConnectionUtil.getConn();
//            threadLocal.set(connection);
//        }
        ConnectionUtil.getConn().setAutoCommit(false);
    }

    // 提交事务
    public static void commit() throws SQLException {
        ConnectionUtil.getConn().commit();
        // 提交事务之后,统一关闭connect
        ConnectionUtil.closeConnection();
    }

    // 回滚事务
    public static void rollback() throws SQLException {
        ConnectionUtil.getConn().rollback();
        // rollback之后,也需要统一关闭connect
        ConnectionUtil.closeConnection();
    }
}
