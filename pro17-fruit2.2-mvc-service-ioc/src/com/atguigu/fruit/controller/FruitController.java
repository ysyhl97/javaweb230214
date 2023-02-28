package com.atguigu.fruit.controller;


import com.atguigu.fruit.biz.FruitService;
import com.atguigu.fruit.dao.FruitDao;

import com.atguigu.fruit.dao.impl.FruitDaoImpl;
import com.atguigu.fruit.pojo.Fruit;
import com.atguigu.myssm.myspringmvc.ViewBaseServlet;
import com.atguigu.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FruitController {


    private FruitService fruitService = null;

    private String update(Integer fid, String fname, Integer price, Integer fcount, String remark) throws ServletException, IOException {

        //3.执行更新
        fruitService.updateFruit(new Fruit(fid, fname, price, fcount, remark));

        //4.资源跳转
        return "redirect:fruit.do";
    }

    private String edit(Integer fid, HttpServletRequest request) throws IOException, ServletException {

        if (fid != null) {
            Fruit fruit = fruitService.getFruitById(fid);
            request.setAttribute("fruit", fruit);
            return "edit";
        }
        return "error";

    }

    private String delete(Integer fid) throws IOException, ServletException {
        if (fid != null) {
            fruitService.deleteFruit(fid);
            return "redirect:fruit.do";
        }
        return "error";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) throws ServletException, IOException {
        Fruit fruit = new Fruit(null, fname, price, fcount, remark);
        fruitService.addFruit(fruit);
        return "redirect:fruit.do";
    }

    private String index(Integer pageNumber, String oper, String keyword, HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 设置当前页，默认值1
        if (pageNumber == null) {
            pageNumber = 1;
        }

        if (StringUtil.isNotEmpty(oper) && "search".equals(oper)) {
            pageNumber = 1;
            if (StringUtil.isEmpty(keyword)) {
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        } else {
            Object keywordObj = session.getAttribute("keyword");
            if (keywordObj != null) {
                keyword = (String) keywordObj;
            } else {
                keyword = "";
            }
        }

        // 重新更新当前页的值
        session.setAttribute("pageNumber", pageNumber);

        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNumber);
        session.setAttribute("fruitList", fruitList);

        //总页数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount", pageCount);
        return "index";
    }
}
