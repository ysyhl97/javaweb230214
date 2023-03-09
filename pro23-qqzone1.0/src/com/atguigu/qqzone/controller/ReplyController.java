package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * ClassName: HostReplyController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/7 10:29
 * @Version 1.0
 */

public class ReplyController {
    private ReplyService replyService;
    private HostReplyService hostReplyService;

    public String addReply(String content, HttpSession session) {
        Topic topic = (Topic) session.getAttribute("topic");
        // 回复的作者就是当前登录的用户
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(null, content, LocalDateTime.now(), userBasic, topic);
//        int a= 1/0;
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topic.getId();
    }

    /**
     * 这是我自己的删除回复及对应的主人回复
     * 从session中获取topic的id
     *
     * @param replyId
     * @param session
     * @return
     */
  /*  public String delReply(String replyId, HttpSession session) {
        // 删除回复之前,应该先查询是否有主人回复;如果有主人回复,要删除对应的主人回复后,才能删除
        hostReplyService.deleteHostReplyByReplyId(replyId);
        replyService.deleteReplyById(replyId);
        Topic topic = (Topic) session.getAttribute("topic");
        return "redirect:topic.do?operate=topicDetail&id=" + topic.getId();
    }*/
    public String deleteReply(String replyId, String topicId) {
        replyService.deleteReplyById(replyId);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
