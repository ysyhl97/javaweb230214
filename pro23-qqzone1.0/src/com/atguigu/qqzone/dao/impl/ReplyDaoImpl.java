package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.base.BaseDao;
import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;

import java.util.List;

/**
 * ClassName: ReplyDaoImpl
 * Package: com.atguigu.qqzone.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 13:50
 * @Version 1.0
 */

public class ReplyDaoImpl extends BaseDao<Reply> implements ReplyDao {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        String sql = "select * from t_reply where topic = ? ";
        return executeQuery(sql, topic.getId());
    }

    @Override
    public void insertReply(Reply reply) {
        String sql = "insert into t_reply values(null,?,?,?,?)";
        executeUpdate(sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void deleteReplyById(String replyId) {
        executeUpdate("delete from t_reply where id = ?", replyId);
    }

    @Override
    public Reply getReply(String replyId) {

        return load("select * from t_reply where id = ?", replyId);
    }
}
