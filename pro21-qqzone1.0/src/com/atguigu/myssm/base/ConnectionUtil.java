package com.atguigu.myssm.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ClassName: ConnectionUtil
 * Package: com.atguigu.myssm.base
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/1 14:07
 * @Version 1.0
 */

public class ConnectionUtil {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/qqzonedb?useUnicode=true&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "root";
    public static final String PWD = "123456";

    public static Connection getConn() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = createConnect();
            threadLocal.set(connection);
        }
        return connection;
    }

    public static Connection createConnect() {
        try {
            //1.加载驱动
            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            return;
        }
        if (!connection.isClosed()) {
            connection.close();
            threadLocal.set(null);
        }
    }
}
