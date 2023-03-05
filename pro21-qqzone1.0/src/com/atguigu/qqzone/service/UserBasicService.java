package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: UserBasicService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:41
 * @Version 1.0
 */

public interface UserBasicService {

    UserBasic login(String loginId, String pwd);

    List<UserBasic> getFriendList(UserBasic userBasic);
}
