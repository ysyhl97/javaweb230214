package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.TopicService;

import java.util.List;

/**
 * ClassName: TopicServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 14:58
 * @Version 1.0
 */

public class TopicServiceImpl implements TopicService {
   private TopicDao topicDao;
   @Override
   public List<Topic> getTopicList(UserBasic userBasic) {
      List<Topic> topicList = topicDao.getTopicList(userBasic);
      return topicList;
   }
}
