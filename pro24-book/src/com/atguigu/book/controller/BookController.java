package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: BookController
 * Package: com.atguigu.book.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 23:48
 * @Version 1.0
 */

public class BookController {

   private BookService bookService;

   public String index(HttpSession session){
      List<Book> bookList = bookService.getBookList();
      session.setAttribute("bookList",bookList);
      return "index";
   }
}
