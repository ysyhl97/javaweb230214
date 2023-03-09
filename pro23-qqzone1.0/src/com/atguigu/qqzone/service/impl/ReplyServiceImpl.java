package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.ReplyDao;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.HostReplyService;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.UserBasicService;

import java.util.List;

/**
 * ClassName: ReplyServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 13:48
 * @Version 1.0
 */

public class ReplyServiceImpl implements ReplyService {

    private ReplyDao replyDao;
    private HostReplyService hostReplyService;
    private UserBasicService userBasicService;

    @Override
    public List<Reply> getReplyListByTopicId(Integer topicId) {
        List<Reply> replyList = replyDao.getReplyList(new Topic(topicId));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            // 获取回复对应的主人回复
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            // 获取回复对应的作者
            UserBasic replyAuthor = userBasicService.getUserBasicById(reply.getAuthor().getId());

            reply.setHostReply(hostReply);
            reply.setAuthor(replyAuthor);
        }

        return replyList;
    }

    @Override
    public void addReply(Reply reply) {
        replyDao.insertReply(reply);
    }

    /**
     * 根据replyId删除reply
     * @param replyId
     */
    @Override
    public void deleteReplyById(String replyId) {
        // 1. 根据replyId获取reply
        Reply reply = getReplyById(replyId);
        //
        if (reply != null) {
            // 2.如果有reply关联的hostReply,则先删除hostReply
            if (reply.getHostReply() != null) {
                // 删除reply对应的hostReply
                hostReplyService.deleteHostReplyByReplyId(replyId);
            }
            replyDao.deleteReplyById(replyId);
        }
    }

    /**
     * 给reply里面封装对应的hostReply
     * @param id
     * @return
     */
    @Override
    public Reply getReplyById(String id) {

        // 根据id查询reply,此时还没有hostReply
        Reply reply = replyDao.getReply(id);
        // 查询reply相应的hostreply
        HostReply hostReplyByReply = hostReplyService.getHostReplyByReplyId(Integer.parseInt(id));
        reply.setHostReply(hostReplyByReply);
        return reply;
    }
}
