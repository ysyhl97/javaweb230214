package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBaseDao
 * Package: com.atguigu.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:06
 * @Version 1.0
 */

public interface UserBasicDao {

    // 查询指定用户的所有好友
    public List<UserBasic> getUserBasicList(UserBasic userBasic);

    // 根据账号密码获取用户信息
    public UserBasic getUserBasic(String loginId, String pwd);

    // 根据id查询用户信息
    UserBasic getUserBasicById(Integer id);
}
