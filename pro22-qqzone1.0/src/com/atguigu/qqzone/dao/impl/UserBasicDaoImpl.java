package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.base.BaseDao;
import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicDaoImpl
 * Package: com.atguigu.qqzone.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:24
 * @Version 1.0
 */

public class UserBasicDaoImpl extends BaseDao<UserBasic> implements UserBasicDao {
    @Override
    public List<UserBasic> getUserBasicList(UserBasic userBasic) {
        String sql = "select fid as id from t_friend where uid = ?";
        return executeQuery(sql, userBasic.getId());
    }

    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
         String sql = "select * from t_user_basic where loginId = ? and pwd = ?";
        return load(sql, loginId, pwd);
    }

    @Override
    public UserBasic getUserBasicById(Integer id) {
        String sql = "select * from t_user_basic where id = ?";
        return  load(sql, id);
    }
}
