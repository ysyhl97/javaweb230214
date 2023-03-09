package com.atguigu.book.pojo;

/**
 * ClassName: OrderItem
 * Package: com.atguigu.book.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:20
 * @Version 1.0
 */

public class OrderItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User orderBean;

   public OrderItem() {
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

    public User getOrderBean() {
        return orderBean;
    }

    public void setOrderBean(User orderBean) {
        this.orderBean = orderBean;
    }
}
