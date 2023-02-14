package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitDao
 * Package: com.atguigu.fruit.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/1 10:56
 * @Version 1.0
 */

public interface FruitDao {

    /**
     * 查询所有库存
     * @return
     */
    List<Fruit> getAllFruit();

    /**
     * 添加水果
     * @param fruit
     * @return
     */
    Integer addFruit(Fruit fruit);

    /**
     * 根据id修改水果信息
     * @param fruit
     * @return
     */
    Integer updateFruit(Fruit fruit);

    /**
     * 根据id删除水果信息
     * @param id
     * @return
     */
    Integer deleteFruit(Integer id);
}
