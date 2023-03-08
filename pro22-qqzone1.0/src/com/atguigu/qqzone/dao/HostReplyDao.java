package com.atguigu.qqzone.dao;

import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDao
 * Package: com.atguigu.qqzone.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 14:58
 * @Version 1.0
 */

public interface HostReplyDao {

    HostReply getHostReplyByReplyId(Integer replyId);

    void insertHostReply(HostReply hostReply);

    void deleteHostReplyByReplyId(String replyId);

}
