package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.dao.UserBasicDao;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
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

    private ReplyService replyService;

    private UserBasicDao userBasicDao;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        List<Topic> topicList = topicDao.getTopicList(userBasic);
        return topicList;
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    /**
     * 原来topic中的author对象在数据库中查到的结果值author_id
     * 通过getTopic()来将topic关联的author对象完整的封装到topic中
     *
     * @param id
     * @return
     */
    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDao.getTopicById(id);
        UserBasic author = topic.getAuthor();
        UserBasic authorUserBasic = userBasicDao.getUserBasicById(author.getId());
        topic.setAuthor(authorUserBasic);
        return topic;
    }

    /**
     * 将删除日志下的回复和主人回复封装到里面
     *
     * @param topic
     */
    @Override
    public void deleteTopic(Integer topicId) {
        // 现在需要删除所有回复和主人回复
        // 首先相到的是replyServie中有删除回复和回复对应的主人回复

        // topicDao.getTopiicById是从数据库中根据topId查找,里面没有封装replyList
//        Topic topic = topicDao.getTopicById(topicId);
        // sevcie中的getTopicById中封装了replyList,所以需要调用这个方法
        Topic topic = getTopicById(topicId);
        for (Reply reply : topic.getReplyList()) {
            replyService.deleteReplyById(reply.getId().toString());
        }

        // 删除日志
        topicDao.deleteTopic(topic.getId());
    }


}
