package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.annotation.WebServlet;
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
            // 为了实现跳转回自己空间,需要区分自己和别人的空间
            // 添加friend这个key,用来保存当前进入的是谁的空间
            // 上面的userBasic这个key,用来保存登录者的信息
            session.setAttribute("friend",userBasic);

            return "index";
        } else {
            return "login";
        }
    }

    public String friend(Integer id,HttpSession session){
        UserBasic friend = userBasicService.getUserBasicById(id);

        //获取好友列表
        List<UserBasic> friendList = userBasicService.getFriendList(friend);
        // 获取日志列表
        List<Topic> topicList = topicService.getTopicList(friend);

        friend.setFriendList(friendList);
        friend.setTopicList(topicList);

        session.setAttribute("friend",friend);
        return "index";
    }
}
