package com.atguigu.qqzone.controller;

import com.atguigu.qqzone.pojo.HostReply;
import com.atguigu.qqzone.pojo.Reply;
import com.atguigu.qqzone.pojo.Topic;
import com.atguigu.qqzone.pojo.UserBasic;
import com.atguigu.qqzone.service.ReplyService;
import com.atguigu.qqzone.service.TopicService;
import com.atguigu.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: TopicController
 * Package: com.atguigu.qqzone.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/6 11:23
 * @Version 1.0
 */

public class TopicController {
    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session) {
        // 根据topicid获取topic对象
        Topic topic = topicService.getTopicById(id);
        // 根据topid获取所有回复
//        List<Reply> replyList = replyService.getReplyListByTopicId(id);
//        session.setAttribute("reply",replyList);
        // reply对象里面封装者authorid
//        for (Reply reply : replyList) {
//            UserBasic replyUserBasic = userBasicService.getUserBasicById(reply.getId());
//
//        }
        // 封装到topic对象中
//        topic.setAuthor(null);
//        topic.setReplyList(replyList);
        session.setAttribute("topic", topic);

        return "frames/detail";
    }


    public String delTopic(Integer topicId, Integer userBasicId) {


        // 删除日志需要删除日志中的所有回复以及对应的主人回复
        // topic中有replyList(所有回复),然后根据所有回复再查询=它们的主人回复
        // thymeleaf不能传object
//        List<Reply> replyList = topic.getReplyList();
   
    
    /*
        // 这个方法里面封装了replylist
        Topic topic = topicService.getTopicById(topicId);

        for (Reply reply : topic.getReplyList()) {
            replyService.deleteReplyById(reply.getId().toString());
        }
*/
        // 这里不用再去查询reply的hostReply,前面删除reply时就已经封装好了删除对应的hostReply
        // 这里需要的时replyid,但我给的是topicId
//        replyService.deleteReplyById(topicId.toString());


        // 删除topic(把删除reply和hostReply封装到service中)
        topicService.deleteTopic(topicId);

//        return "redirect:user.do?operate=friend&id="+userBasicId;
        return "redirect:topic.do?operate=getTopicList";
    }

    /**
     * 更新删除后的topiclist
     *
     * @return
     */
    public String getTopicList(HttpSession session) {

        // 从session中获取当前的用户信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        // 再次查询关联用户的所有日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //设置一下关联的日志列表(因为之前session中关联的friend的topicList和此刻数据库中不一致）
        userBasic.setTopicList(topicList);

        //重新覆盖一下friend中的信息(为什么不覆盖userbasic中？因为main.html页面迭代的是friend这个key中的数据)
        session.setAttribute("friend", userBasic);

        return "index";
    }
}
