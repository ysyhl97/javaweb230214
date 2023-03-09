package com.atguigu.book.service.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.service.BookService;

import java.util.List;

/**
 * ClassName: BookServiceImpl
 * Package: com.atguigu.book.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 23:46
 * @Version 1.0
 */

public class BookServiceImpl implements BookService {

   private BookDao bookDao;
   @Override
   public List<Book> getBookList() {
      return bookDao.selectBookList();
   }

   public Book getBookById(Integer id){
      return bookDao.selectBookById(id);
   }
}
