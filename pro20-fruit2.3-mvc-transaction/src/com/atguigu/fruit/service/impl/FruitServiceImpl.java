package com.atguigu.fruit.service.impl;

import com.atguigu.fruit.service.FruitService;
import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.pojo.Fruit;

import java.util.List;

/**
 * ClassName: FruitServiceImpl
 * Package: com.atguigu.fruit.biz.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/28 14:00
 * @Version 1.0
 */

public class FruitServiceImpl implements FruitService {
    private FruitDao fruitDao = null;

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNumber) {
        return fruitDao.getAllFruit(keyword, pageNumber);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDao.addFruit(fruit);
    }

    @Override
    public Fruit getFruitById(Integer fid) {
        return fruitDao.getFruitById(fid);
    }

    @Override
    public void deleteFruit(Integer fid) {
        fruitDao.deleteFruitById(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        Integer count = fruitDao.getFruitCount(keyword);
        int pageCount = (count + 5 - 1) / 5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDao.updateFruitById(fruit);
    }
}
