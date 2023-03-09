package com.atguigu.myssm.base;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
//    public static String DRIVER;
//    public static String URL;
//    public static String USER;
//    public static String PWD;


    static Properties properties = new Properties();

    static {
        InputStream is = ConnectionUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            properties.load(is);

//            DRIVER = properties.getProperty("jdbc.driver");
//            URL = properties.getProperty("jdbc.url");
//            USER = properties.getProperty("jdbc.user");
//            PWD = properties.getProperty("jdbc.pwd");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


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
            // 使用数据库连接池
//            DruidDataSource dataSource = new DruidDataSource();
//            dataSource.setDriverClassName(DRIVER);
//            dataSource.setUrl(URL);
//            dataSource.setUsername(USER);
//            dataSource.setPassword(PWD);
//
//            dataSource.setMaxWait(5000);
//            dataSource.setMinIdle(3);
//            dataSource.setMaxActive(10);

            // 通过properties文件加载DataSource
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);


            return dataSource.getConnection();
            //1.加载驱动
//            Class.forName(DRIVER);
            //2.通过驱动管理器获取连接对象
//            return DriverManager.getConnection(URL, USER, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
