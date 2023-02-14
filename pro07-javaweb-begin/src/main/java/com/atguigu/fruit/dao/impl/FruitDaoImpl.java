package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitDaoImpl
 * Package: com.atguigu.fruit.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/1 11:08
 * @Version 1.0
 */

public class FruitDaoImpl extends BaseDAO<Fruit> implements FruitDao {
    @Override
    public List<Fruit> getAllFruit() {
        String sql = "select * from t_fruit";
        List<Fruit> fruitList = executeQuery(sql);
        for (Fruit fruit : fruitList) {
            System.out.println("fruit = " + fruit);
        }
        return fruitList;
    }

    @Override
    public Integer addFruit(Fruit fruit) {
        String sql = "insert into t_fruit(fname,price,fcount,remark) values(?,?,?,?)";
        int executeUpdate = executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
        return executeUpdate;
    }

    @Override
    public Integer updateFruit( Fruit fruit) {
        return null;
    }

    @Override
    public Integer deleteFruit(Integer id) {
        return null;
    }
}
