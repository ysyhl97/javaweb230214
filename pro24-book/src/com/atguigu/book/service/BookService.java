package com.atguigu.book.service;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * ClassName: BookService
 * Package: com.atguigu.book.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 23:45
 * @Version 1.0
 */

public interface BookService {
   List<Book> getBookList();
   Book getBookById(Integer id);
}
