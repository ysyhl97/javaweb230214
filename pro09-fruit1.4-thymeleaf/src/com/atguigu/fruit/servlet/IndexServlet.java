package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: IndexServlet
 * Package: com.atguigu.fruit.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 10:58
 * @Version 1.0
 */

public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        FruitDaoImpl fruitDao = new FruitDaoImpl();
        List<Fruit> fruitList = fruitDao.getAllFruit();
        session.setAttribute("fruitList",fruitList);

//       将视图名称index加上前缀和后缀,对应到物理视图  /index.html页面
        super.processTemplate("index",req,resp);
    }
}
