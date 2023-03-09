package com.atguigu.qqzone.service;

import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyService
 * Package: com.atguigu.qqzone.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 15:04
 * @Version 1.0
 */

public interface HostReplyService {

    HostReply getHostReplyByReplyId(Integer replyId);

    void addHostReply(HostReply hostReply);

    void deleteHostReplyByReplyId(String replyId);
}
