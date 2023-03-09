package com.atguigu.myssm.myspringmvc;

import javax.servlet.annotation.WebServlet;

/**
 * ClassName: PageController
 * Package: com.atguigu.myssm.myspringmvc
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/4 22:28
 * @Version 1.0
 */
@WebServlet
public class PageController {
   public String page(String page){
      return page;
   }
}
