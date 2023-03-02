package com.atguigu.fruit.service;

import com.atguigu.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.List;

/**
 * ClassName: FruitService
 * Package: com.atguigu.fruit.biz
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/28 13:54
 * @Version 1.0
 */

public interface FruitService {
    //获取指定页面的库存列表信息
    List<Fruit> getFruitList(String keyword, Integer pageNumber);

    //添加库存记录信息
    void addFruit(Fruit fruit) ;

    //根据id查询指定库存记录
    Fruit getFruitById(Integer fid) ;

    //删除特定库存记录
    void deleteFruit(Integer fid) ;

    //获取总页数
    Integer getPageCount(String keyword) ;

    //更新指定库存信息
    void updateFruit(Fruit fruit) ;
}
