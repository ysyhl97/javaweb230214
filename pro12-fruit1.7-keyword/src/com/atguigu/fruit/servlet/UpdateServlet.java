package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.FruitDao;
import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: UpdateServlet
 * Package: com.atguigu.fruit.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/3 11:45
 * @Version 1.0
 */
@WebServlet("/update.do")
public class UpdateServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        int fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");
        String fidStr = request.getParameter("fid");
        int fid = Integer.parseInt(fidStr);
        fruitDao.updateFruitById(new Fruit(fid, fname, price, fcount, remark));


        //我不清楚这里使用请求转发,为什么会报405的错误,改成重定向就好了?
        //原因就是服务器是以post方式向index发送请求的,但是indexServlet没有对应的doPost()方法来处理该请求
//        request.getRequestDispatcher("index").forward(request,response);
        //通过模板渲染,虽然数据库中已更新,session域中的数据没有更新.页面上还是旧数据
//        super.processTemplate("index",request,response);
        //此处需要重定向，目的是重新给IndexServlet发请求，重新获取furitList，
        //然后覆盖到session中，这样index.html页面上显示的session中的数据才是最新的
        response.sendRedirect("index");

    }


}
