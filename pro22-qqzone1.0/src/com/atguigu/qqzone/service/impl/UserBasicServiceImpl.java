package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserBasicServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:43
 * @Version 1.0
 */

public class UserBasicServiceImpl implements UserBasicService {

   private UserBasicDao userBasicDao;
   @Override
   public UserBasic login(String loginId, String pwd) {
      return userBasicDao.getUserBasic(loginId, pwd);
   }

   @Override
   public List<UserBasic> getFriendList(UserBasic userBasic) {
      // 查询用户的所有好友(但是此时好友列表里面封装的fid),所以需要根据fid再查询一次
      List<UserBasic> userBasicList = userBasicDao.getUserBasicList(userBasic);
      // 创建friendList用来存放
      List<UserBasic> friendList = new ArrayList<>(userBasicList.size());
      for (UserBasic user : userBasicList) {
         friendList.add(userBasicDao.getUserBasicById(user.getId()));
      }
      return friendList;
   }
}
