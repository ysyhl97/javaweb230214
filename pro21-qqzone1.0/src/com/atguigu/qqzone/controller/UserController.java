package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: UserController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 11:54
 * @Version 1.0
 */

public class UserController {

    private UserBasicService userBasicService;
    private TopicService topicService;

    public String login(String login, String pwd, HttpSession session) {
        UserBasic userBasic = userBasicService.login(login, pwd);
        if (userBasic != null) {
            // 验证成功需要跳转至index
            // 而index页面需要显示好友列表和日志

            // 获得好友列表
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            // 获取日志
            List<Topic> topicList = topicService.getTopicList(userBasic);

            // 将数据封装到userBasic对象中
            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            // 将userBasic对象存储到session域中
            session.setAttribute("userBasic", userBasic);

            return "index";
        } else {
            return "login";
        }
    }
}
