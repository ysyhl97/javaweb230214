package com.atguigu.qqzone.service;

import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 14:56
 * @Version 1.0
 */

public interface TopicService {

    List<Topic> getTopicList(UserBasic userBasic);
    Topic getTopicById(Integer id);

    /**
     * 根据id获取获取指定Topic信息,包含这个Topic关联的author信息
     * @param id
     * @return
     */
    Topic getTopic(Integer id);

    void deleteTopic(Integer topicId);

}
