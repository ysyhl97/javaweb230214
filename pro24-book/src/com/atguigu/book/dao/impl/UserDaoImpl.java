package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.UserDao;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;

/**
 * ClassName: UserDaoImpl
 * Package: com.atguigu.book.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:50
 * @Version 1.0
 */

public class UserDaoImpl extends BaseDAO<User> implements UserDao {
   @Override
   public User selectUser(String uname, String pwd) {
     return load("select * from t_user where uname like ? and pwd like ?",uname,pwd);
   }
}
