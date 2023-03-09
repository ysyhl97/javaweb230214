package com.atguigu.book.dao;

import com.atguigu.book.pojo.Book;

import java.util.List;

/**
 * ClassName: BookDao
 * Package: com.atguigu.book.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 23:43
 * @Version 1.0
 */

public interface BookDao {
   List<Book> selectBookList();

   Book selectBookById(Integer id);
}
