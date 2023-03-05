package com.atguigu.qqzone.pojo;

import java.time.LocalDateTime;

/**
 * ClassName: Reply
 * Package: com.atguigu.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 10:41
 * @Version 1.0
 */

public class Reply {
    private Integer id;
    private String content;
    private LocalDateTime replyDate;
    private UserBasic author; //n:1
    private Topic topic; //n:1

    public HostReply getHostReply() {
        return hostReply;
    }

    public void setHostReply(HostReply hostReply) {
        this.hostReply = hostReply;
    }

    private HostReply hostReply; //1:1

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime replyDate) {
        this.replyDate = replyDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Reply() {
    }
}
