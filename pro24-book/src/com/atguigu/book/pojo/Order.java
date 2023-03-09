package com.atguigu.book.pojo;

import java.util.Date;
import java.util.List;

/**
 * ClassName: Order
 * Package: com.atguigu.book.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:25
 * @Version 1.0
 */

public class Order {
   private Integer id;
   private String orderNo;
   private Date orderDate;
   private User orderUser;
   private Double orderMoney;
   private Integer orderStatus=0;

   private List<OrderItem> orderItemList;

   public Order() {
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getOrderNo() {
      return orderNo;
   }

   public void setOrderNo(String orderNo) {
      this.orderNo = orderNo;
   }

   public Date getOrderDate() {
      return orderDate;
   }

   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }

   public User getOrderUser() {
      return orderUser;
   }

   public void setOrderUser(User orderUser) {
      this.orderUser = orderUser;
   }

   public Double getOrderMoney() {
      return orderMoney;
   }

   public void setOrderMoney(Double orderMoney) {
      this.orderMoney = orderMoney;
   }

   public Integer getOrderStatus() {
      return orderStatus;
   }

   public void setOrderStatus(Integer orderStatus) {
      this.orderStatus = orderStatus;
   }

   public List<OrderItem> getOrderItemList() {
      return orderItemList;
   }

   public void setOrderItemList(List<OrderItem> orderItemList) {
      this.orderItemList = orderItemList;
   }
}
