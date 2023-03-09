package com.atguigu.qqzone.pojo;


import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: Topic
 * Package: com.atguigu.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/3 10:38
 * @Version 1.0
 */

public class Topic {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime topicDate;
    private UserBasic author; // author数据库中为Int

    private List<Reply> replyList; // 1:n

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getContent() {
        return content;
    }


    public LocalDateTime getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
    }

    public UserBasic getAuthor() {
        return author;
    }

    public void setAuthor(UserBasic author) {
        this.author = author;
    }


    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }
}
