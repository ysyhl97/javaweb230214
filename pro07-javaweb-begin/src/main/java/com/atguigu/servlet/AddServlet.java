package com.atguigu.servlet;

import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AddServlet
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/1 9:48
 * @Version 1.0
 */

public class AddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        String fprice = request.getParameter("price");
        String fcount = request.getParameter("fcount");
        String remark = request.getParameter("remark");

        System.out.println("fname = " + fname);
        System.out.println("fprice = " + fprice);
        System.out.println("fcount = " + fcount);
        System.out.println("remark = " + remark);

        FruitDaoImpl fruitDao = new FruitDaoImpl();
        Integer integer = fruitDao.addFruit(new Fruit(null, fname, Integer.valueOf(fprice), Integer.valueOf(fcount), remark));
        System.out.println(integer);
    }
}
