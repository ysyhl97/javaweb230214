package com.atguigu.fruit.servlet;

import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        FruitDaoImpl fruitDao = new FruitDaoImpl();
        Integer pageNumber = 1;

        String pageNumberStr = request.getParameter("pageNumber");
        if (StringUtil.isNotEmpty(pageNumberStr)) {
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        session.setAttribute("pageNumber", pageNumber);

        List<Fruit> fruitList = fruitDao.getAllFruit(pageNumber);
        session.setAttribute("fruitList", fruitList);

        //获取总记录数
        Integer fruitCount = fruitDao.getFruitCount();
        //获取尾页
        Integer pageCount = (fruitCount + 5 - 1) / 5;

        session.setAttribute("pageCount",pageCount);

//       将视图名称index加上前缀和后缀,对应到物理视图  /index.html页面
        super.processTemplate("index", request, response);
//        response.sendRedirect("index");
//        request.getRequestDispatcher("index").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
