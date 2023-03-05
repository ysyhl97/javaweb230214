package com.atguigu.myssm.IOC;

/**
 * ClassName: BeanFactory
 * Package: com.atguigu.myssm.IOC
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 15:23
 * @Version 1.0
 */

public interface BeanFactory {

    Object getBean(String id);
}
