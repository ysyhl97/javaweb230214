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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ClassName: FruitServlet
 * Package: com.atguigu.fruit.servlet
 * Description:
 * 使用FruitServlet来代替其他Servlet
 *
 * @Author ysyhl97
 * @Create 2023/2/9 14:35
 * @Version 1.0
 */

@WebServlet("/index")
public class FruitServlet extends ViewBaseServlet {

    private FruitDao fruitDao = new FruitDaoImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) {
            operate = "index";
        }

        switch (operate) {
            case "index":
                index(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "update":
                update(request, response);
                break;
            default:

        }

    }

    /**
     * index方法用来代替IndexServlet
     *
     * @param request
     * @param response
     * @throws
     *
     */
    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String keyword;

        //设置当前页,默认为首页
        Integer pageNumber = 1;
        //通过oper来判断是否为关键字查询请求
        String oper = request.getParameter("oper");
        //如果oper!=null && oper="search",说明是通过表单查询按钮发送的请求
        //oper==null,说明不是通过表单查询按钮发送的请求
        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            //让页码复位到首页
            pageNumber = 1;
            //从请求参数中获取keyword
            keyword = request.getParameter("keyword");
            //防止为null时,在sql中进行拼接%null%
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            //将keyword保存(覆盖)到session中
            session.setAttribute("keyword", keyword);
        } else {
            //从请求参数中获取页码
            String pageNumberStr = request.getParameter("pageNumber");
            //如果有pageNumber,转为Integer
            //若没有pageNumber为默认值1,即当前为首页
            if (StringUtil.isNotEmpty(pageNumberStr)) {
                pageNumber = Integer.parseInt(pageNumberStr);
            }

            //从session中获取keyword
            Object keywordObj = session.getAttribute("keyword");
            //不为null转为String,否则转为空字符串
            if (keywordObj != null) {
                keyword = keywordObj.toString();
            } else {
                keyword = "";
            }

        }
        //将页码更新到session
        session.setAttribute("pageNumber", pageNumber);

        List<Fruit> fruitList = fruitDao.getAllFruit(keyword, pageNumber);
        session.setAttribute("fruitList", fruitList);

        //获取库存总记录数
        Integer fruitCount = fruitDao.getFruitCount(keyword);
        //获取总页数
        Integer pageCount = (fruitCount + 5 - 1) / 5;
        session.setAttribute("pageCount", pageCount);

        //视图跳转(thymeleaf)
        super.processTemplate("index", request, response);
    }

    /**
     * add()代替AddServlet
     *
     * @param request
     * @param response
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer price = null;
        Integer fcount = null;

        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        if (StringUtil.isNotEmpty(priceStr)) {
            price = Integer.parseInt(priceStr);
        }
        String fcountStr = request.getParameter("fcount");
        if (StringUtil.isNotEmpty(fcountStr)) {
            fcount = Integer.parseInt(fcountStr);
        }
        String remark = request.getParameter("remark");

        fruitDao.addFruit(new Fruit(null, fname, price, fcount, remark));

        //视图渲染
        //不使用thymeleaf,是因为index页面数据是从session中获取;数据库中数据更新后session中的未更新
        response.sendRedirect("index");

    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer fid = null;
        String fidStr = request.getParameter("fid");
        if (StringUtil.isNotEmpty(fidStr)) {
            fid = Integer.parseInt(fidStr);
        }

        fruitDao.deleteFruitById(fid);

        //视图处理
        response.sendRedirect("index");

    }

    /**
     * 实现跳转edit.html,并将数据回显
     *
     * @param request
     * @param response
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        Fruit fruit = fruitDao.getFruitById(fid);
        request.getSession().setAttribute("fruit", fruit);

        super.processTemplate("edit", request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer price = null;
        Integer fcount = null;
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        if (StringUtil.isNotEmpty(priceStr)) {
            price = Integer.parseInt(priceStr);
        }
        String fcountStr = request.getParameter("fcount");
        if (StringUtil.isNotEmpty(fcountStr)) {
            fcount = Integer.parseInt(fcountStr);
        }
        String remark = request.getParameter("remark");
        Object fruit = request.getSession().getAttribute("fruit");
        Fruit fruit1 = (Fruit) fruit;
        Integer fid = fruit1.getFid();

        fruitDao.updateFruitById(new Fruit(fid, fname, price, fcount, remark));

        response.sendRedirect("index");

    }
}
