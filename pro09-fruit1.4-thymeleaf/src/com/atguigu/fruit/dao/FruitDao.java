package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitDao
 * Package: com.atguigu.fruit.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 10:51
 * @Version 1.0
 */

public interface FruitDao {

    /**
     * 查询所有库存信息
     * @return
     */
    List<Fruit> getAllFruit();
}
