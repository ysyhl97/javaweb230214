package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Reply;

import java.util.List;

/**
 * ClassName: ReplyService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 13:45
 * @Version 1.0
 */

public interface ReplyService {

   List<Reply> getReplyListByTopicId(Integer topicId);

   void addReply(Reply reply);

   void deleteReplyById(String replyId);

   Reply getReplyById(String id);
}
