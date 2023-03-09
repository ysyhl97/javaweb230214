package com.atguigu.qqzone.dao.impl;

import com.atguigu.myssm.base.BaseDao;
import com.atguigu.qqzone.dao.HostReplyDao;
import com.atguigu.qqzone.pojo.HostReply;

/**
 * ClassName: HostReplyDaoImpl
 * Package: com.atguigu.qqzone.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 15:00
 * @Version 1.0
 */

public class HostReplyDaoImpl extends BaseDao<HostReply> implements HostReplyDao {
   @Override
   public HostReply getHostReplyByReplyId(Integer replyId) {
      String sql = "select * from t_host_reply where reply = ? ";
      return load(sql,replyId);
   }

   @Override
   public void insertHostReply(HostReply hostReply) {
      String sql = "insert into t_host_reply values(null,?,?,?,?)";
      load(sql,hostReply.getContent(),hostReply.getHostReplyDate(),hostReply.getAuthor().getId(),hostReply.getReply().getId());
   }

   @Override
   public void deleteHostReplyByReplyId(String replyId) {
      executeUpdate("delete from t_host_reply where reply = ?",replyId);
   }
}
