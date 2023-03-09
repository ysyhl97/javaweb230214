package com.atguigu.myssm.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/**
 * ClassName: MySessionListener
 * Package: com.atguigu.myssm.listener
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/7 12:34
 * @Version 1.0
 */
@WebListener
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    private long addTime;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听session创建......");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("监听session销毁");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("监听session中添加数据.....");
        System.out.println("session中添加的属性:" + httpSessionBindingEvent.getName());
        //当前添加数据的时间
        addTime = System.currentTimeMillis();
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("监听session中移除数据....");
        System.out.println("session中移除的数据:" + httpSessionBindingEvent.getName());
        long removeTime = System.currentTimeMillis();
        long onile = (removeTime - addTime) / 1000;
        System.out.println("数据保存: " + onile + "秒");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("session中修改数据 " + httpSessionBindingEvent.getName());
    }
}
