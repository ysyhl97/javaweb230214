package com.atguigu.test;

import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;

/**
 * ClassName: TestDemo01
 * Package: com.atguigu.test
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/1 15:40
 * @Version 1.0
 */

public class TestDemo01 {
   public static void main(String[] args) {
      FruitDaoImpl fruitDao = new FruitDaoImpl();
//      fruitDao.getAllFruit();
      Fruit fruit = new Fruit(null, "冬瓜", 12, 100, "最讨厌冬瓜了!");
      Integer integer = fruitDao.addFruit(fruit);
      System.out.println("integer = " + integer);
   }
}
