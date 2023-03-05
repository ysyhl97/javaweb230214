package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.base.BaseDao;
import com.atguigu.qqzone.dao.TopicDao;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;

import java.util.List;

/**
 * ClassName: TopicDaoImpl
 * Package: com.atguigu.qqzone.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 14:23
 * @Version 1.0
 */

public class TopicDaoImpl extends BaseDao<Topic> implements TopicDao {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        String sql = "select * from t_topic where author = ?";
        return executeQuery(sql, userBasic.getId());
    }

    @Override
    public void insertTopic(Topic topic) {
        String sql = "insert into t_topic values(null,?,?,?,?)";
        load(sql, topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor());
    }

    @Override
    public void deleteTopic(Topic topic) {
        String sql = "delete from t_topic where id = ?";
        load(sql, topic.getId());
    }

    @Override
    public Topic getTopicById(Integer id) {
        String sql = "select * from t_topic where id = ?";
        return load(sql, id);
    }
}
