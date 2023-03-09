package com.atguigu.qqzone.service.impl;

import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.service.HostReplyService;

/**
 * ClassName: HostReplyServiceImpl
 * Package: com.atguigu.qqzone.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 15:05
 * @Version 1.0
 */

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDao hostReplyDao;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDao.getHostReplyByReplyId(replyId);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        hostReplyDao.insertHostReply(hostReply);
    }

    @Override
    public void deleteHostReplyByReplyId(String replyId) {
        hostReplyDao.deleteHostReplyByReplyId(replyId);
    }
}
