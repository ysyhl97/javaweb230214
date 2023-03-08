package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDao
 * Package: com.atguigu.qqzone.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 14:31
 * @Version 1.0
 */

public interface ReplyDao {

   /**
    * 获取指定日志的回复列表
    * @param topic 日志
    * @return 所有回复
    */
   List<Reply> getReplyList(Topic topic);

   /**
    * 添加回复
    * @param reply 回复
    */
   void insertReply(Reply reply);

   /**
    * 删除回复
    * @param replyId
    */
   void deleteReplyById(String replyId);

   /**
    * 根据replyId获取reply
    *
    * @param replyId
    * @return
    */
   Reply getReply(String replyId);
}
