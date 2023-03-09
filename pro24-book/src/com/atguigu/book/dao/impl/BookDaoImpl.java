package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.pojo.Book;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

import static com.alibaba.druid.util.JdbcUtils.executeQuery;

/**
 * ClassName: BookDaoImpl
 * Package: com.atguigu.book.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 23:44
 * @Version 1.0
 */

public class BookDaoImpl extends BaseDAO<Book> implements BookDao {
   @Override
   public List<Book> selectBookList() {
      return executeQuery("select * from t_book");
   }

   @Override
   public Book selectBookById(Integer id) {
      return load("select * from t_book where id = ?",id);
   }
}
