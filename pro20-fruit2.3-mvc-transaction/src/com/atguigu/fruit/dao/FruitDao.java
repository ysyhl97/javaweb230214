package com.atguigu.fruit.dao;

import com.atguigu.fruit.pojo.Fruit;

import java.sql.SQLException;
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
     * 查询所有库存信息(分页)
     * @param pageNumber 当前页码数
     * @param keyword 需查询的关键字
     * @return
     */
    List<Fruit> getAllFruit(String keyword,Integer pageNumber);

    /**
     * 根据id查询水果信息
     *
     * @param id 水果id
     * @return 水果对象
     */
    Fruit getFruitById(Integer id) ;

    /**
     * 根据id修改库存信息
     *
     * @param fruit
     */
    void updateFruitById(Fruit fruit) ;

    /**
     * 根据id删除fruit
     *
     * @param id
     */
    void deleteFruitById(Integer id) ;

    /**
     * 添加fruit
     *
     * @param fruit
     */
    void addFruit(Fruit fruit) ;


    /**
     * 查询总记录数
     * @param keyword 查询的关键字
     * @return
     */
    Integer getFruitCount(String keyword) ;


}
