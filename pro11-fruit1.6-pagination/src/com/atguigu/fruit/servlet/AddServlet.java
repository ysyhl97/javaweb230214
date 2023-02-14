package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AddServlet
 * Package: com.atguigu.fruit.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/7 11:08
 * @Version 1.0
 */
@WebServlet("/add.do")
public class AddServlet extends ViewBaseServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");

        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        Integer price=null;
        Integer fcount=null;
        if (StringUtil.isNotEmpty(priceStr)) {
             price = Integer.parseInt(priceStr);
        }
        String fcountStr = request.getParameter("fcount");
        if (StringUtil.isNotEmpty(fcountStr)) {
             fcount = Integer.parseInt(fcountStr);
        }
        String remark = request.getParameter("remark");

        FruitDao fruitDao = new FruitDaoImpl();
        fruitDao.addFruit(new Fruit(null,fname,price,fcount,remark));

        response.sendRedirect("index");
//        super.processTemplate("index",request,response);
    }
}
