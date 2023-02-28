package com.atguigu.myssm.io;

/**
 * ClassName: BeanFactory
 * Package: com.atguigu.myssm.io
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/28 14:22
 * @Version 1.0
 */

public interface BeanFactory {

    /**
     * 根据id返回对应的class对象
     * @param id
     * @return
     */
    Object getBean(String id);
}
