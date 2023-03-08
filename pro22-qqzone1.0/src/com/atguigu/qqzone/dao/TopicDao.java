package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicDao
 * Package: com.atguigu.qqzone.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 14:19
 * @Version 1.0
 */

public interface TopicDao {
   /**
    * 查询用户的所有日志列表
    * @param userBasic 用户
    * @return 日志列表
    */
   List<Topic> getTopicList(UserBasic userBasic);

   /**
    * 添加日志
    * @param topic 日志
    */
   void insertTopic(Topic topic);

   /**
    * 删除日志
    * @param topicId
    */
   void deleteTopic(Integer topicId);

   /**
    * 根据id查询特定日志
    * @param id
    * @return
    */
   Topic getTopicById(Integer id);
}
