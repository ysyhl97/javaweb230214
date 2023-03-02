package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.base.BaseDAO;

import java.sql.SQLException;
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

    public FruitDaoImpl() throws ClassNotFoundException {
    }

    @Override
    public List<Fruit> getAllFruit(String keyword, Integer pageNumber) {
        String sql = "select * from t_fruit where fname like ? or remark like ? limit ?,5";
        List<Fruit> fruitList = null;

        fruitList = executeQuery(sql, "%" + keyword + "%", "%" + keyword + "%", (pageNumber - 1) * 5);

        return fruitList;
    }

    @Override
    public Fruit getFruitById(Integer id)  {
        String sql = "select * from t_fruit where fid = ?";
        Fruit fruit = load(sql, id);
        return fruit;
    }

    @Override
    public void updateFruitById(Fruit fruit)  {
        String sql = "updat t_fruit set fname=?,price=?,fcount=?,remark=? where fid=?";
        executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
    }

    @Override
    public void deleteFruitById(Integer id)  {
        String sql = "delete from t_fruit where fid=?";
        executeUpdate(sql, id);
    }

    @Override
    public void addFruit(Fruit fruit)  {
        String sql = "insert into t_fruit(fname,price,fcount,remark) values(?,?,?,?)";
        executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());

    }


    @Override
    public Integer getFruitCount(String keyword)  {
        String sql = "select count(*) from t_fruit where fname like ? or remark like ?";
        return ((Long) super.executeComplexQuery(sql, "%" + keyword + "%", "%" + keyword + "%")[0]).intValue();
    }
}
