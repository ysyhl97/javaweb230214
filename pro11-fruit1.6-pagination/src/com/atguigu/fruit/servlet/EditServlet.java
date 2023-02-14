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
 * ClassName: EditServlet
 * Package: com.atguigu.fruit.servlet
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/2/2 17:07
 * @Version 1.0
 */

@WebServlet("/edit.do")
public class EditServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取url中的请求参数
        String fidStr = req.getParameter("fid");
        //若参数不为空
        if (StringUtil.isNotEmpty(fidStr)) {
            Fruit fruit = fruitDao.getFruitById(Integer.valueOf(fidStr));
            req.setAttribute("fruit", fruit);
        }
        super.processTemplate("edit", req, resp);
    }

}
