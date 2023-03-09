package com.atguigu.book.pojo;

/**
 * ClassName: CartItem
 * Package: com.atguigu.book.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:28
 * @Version 1.0
 */

public class CartItem {
   private Integer id;
   private Book book;
   private Integer buyCount;
   private User userBean;

   public CartItem() {
   }

   public CartItem(Book book, User userBean) {
      this.book = book;
      this.userBean = userBean;
   }

   public CartItem(Book book, Integer buyCount, User userBean) {
      this.book = book;
      this.buyCount = buyCount;
      this.userBean = userBean;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Book getBook() {
      return book;
   }

   public void setBook(Book book) {
      this.book = book;
   }

   public Integer getBuyCount() {
      return buyCount;
   }

   public void setBuyCount(Integer buyCount) {
      this.buyCount = buyCount;
   }

   public User getUserBean() {
      return userBean;
   }

   public void setUserBean(User userBean) {
      this.userBean = userBean;
   }
}
