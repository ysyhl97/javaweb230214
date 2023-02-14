package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.base.BaseDAO;

import java.util.List;

/**
 * ClassName: FruitDaoImpl
 * Package: com.atguigu.fruit.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 10:53
 * @Version 1.0
 */

public class FruitDaoImpl extends BaseDAO<Fruit> implements FruitDao {

    @Override
    public List<Fruit> getAllFruit() {
        String sql = "select * from t_fruit";
        List<Fruit> fruitList = executeQuery(sql);
        return fruitList;
    }
}
