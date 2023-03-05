package com.atguigu.myssm.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: BaseDao
 * Package: com.atguigu.myssm.base
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:09
 * @Version 1.0
 */

public abstract class BaseDao<T> {

    protected Connection conn;
    protected PreparedStatement psmt;
    protected ResultSet rs;

    //T的Class对象
    private Class entityClass;

    public BaseDao() {
        //getClass() 获取Class对象，当前我们执行的是new FruitDAOImpl() , 创建的是FruitDAOImpl的实例
        //那么子类构造方法内部首先会调用父类（BaseDAO）的无参构造方法
        //因此此处的getClass()会被执行，但是getClass获取的是FruitDAOImpl的Class
        //所以getGenericSuperclass()获取到的是BaseDAO的Class
        Type genericType = getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        //获取到的<T>中的T的真实的类型
        Type actualType = actualTypeArguments[0];

        try {
            entityClass = Class.forName(actualType.getTypeName());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层BaseDAO错误....");
        }

    }

    protected Connection getConn() {
        // 为了实现事务,需要在service中的操作使用同一个连接
        return ConnectionUtil.getConn();
    }

    protected void close(ResultSet rs, PreparedStatement psmt, Connection conn) {

    }

    //给预处理命令对象设置参数
    private void setParams(PreparedStatement psmt, Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 1, params[i]);
            }
        }
    }

    //执行更新，返回影响行数
    protected int executeUpdate(String sql, Object... params) {
        boolean insertFlag = false;
        insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
        try {
            conn = getConn();
            if (insertFlag) {
                psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            } else {
                psmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            }
            setParams(psmt, params);
            int count = psmt.executeUpdate();

            rs = psmt.getGeneratedKeys();
            if (rs.next()) {
                return ((Long) rs.getLong(1)).intValue();
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层executeUpdate错误....");
        }

    }

    //通过反射技术给obj对象的property属性赋propertyValue值
    private void setValue(Object obj, String property, Object propertyValue) {
        Class clazz = obj.getClass();
        try {
            //获取property这个字符串对应的属性名 ， 比如 "fid"  去找 obj对象中的 fid 属性
            Field field = clazz.getDeclaredField(property);
            if (field != null) {

                // 获取当前字段的类型名称
                String typeName = field.getType().getName();

                // 判断如果是自定义类型,则需要调用这个自定义类的带一个参数的构造方法,创建出这个自定义类型的对象,然后将将实例对象赋值给这个属性
                if (isMyType(typeName)) {
                    Class<?> aClass = Class.forName(typeName);
                    Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(Integer.class);
                    propertyValue = declaredConstructor.newInstance(propertyValue);
                }


                field.setAccessible(true);
                field.set(obj, propertyValue);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层setValue错误....");
        }
    }

    private static boolean isNotMyType(String typeName) {
        return "java.lang.Integer".equals(typeName)
                || "java.lang.String".equals(typeName)
                || "java.util.Date".equals(typeName)
                || "java.sql.Date".equals(typeName)
                || "java.time.LocalDateTime".equals(typeName);
    }

    private static boolean isMyType(String typeName) {
        return !isNotMyType(typeName);
    }

    //执行复杂查询，返回例如统计结果
    protected Object[] executeComplexQuery(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            Object[] columnValueArr = new Object[columnCount];
            //6.解析rs
            if (rs.next()) {
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    columnValueArr[i] = columnValue;
                }
                return columnValueArr;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层executeComplexQuery错误.......");
        }
        return null;
    }

    //执行查询，返回单个实体对象
    protected T load(String sql, Object... params) {

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            if (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                return entity;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层load错误.......");
        }
        return null;
    }


    //执行查询，返回List
    protected List<T> executeQuery(String sql, Object... params) {
        List<T> list = new ArrayList<>();

        conn = getConn();
        try {
            psmt = conn.prepareStatement(sql);
            setParams(psmt, params);
            rs = psmt.executeQuery();

            //通过rs可以获取结果集的元数据
            //元数据：描述结果集数据的数据 , 简单讲，就是这个结果集有哪些列，什么类型等等

            ResultSetMetaData rsmd = rs.getMetaData();
            //获取结果集的列数
            int columnCount = rsmd.getColumnCount();
            //6.解析rs
            while (rs.next()) {
                T entity = (T) entityClass.newInstance();

                for (int i = 0; i < columnCount; i++) {
                    // select fid as id from t_friend where uid = ?
                    // getColumnName获取as前面 fid
                    // getColumnLable获得as后面 id  因为userBasic对象中没有fid,所以需要使用别名id
                    String columnName = rsmd.getColumnLabel(i + 1);
//                    String columnName = rsmd.getColumnName(i + 1);            //fid   fname   price
                    Object columnValue = rs.getObject(i + 1);     //33    苹果      5
                    setValue(entity, columnName, columnValue);
                }
                list.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("dao层executeQuery错误.....");
        }
        return list;
    }

}
