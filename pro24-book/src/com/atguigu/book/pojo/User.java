package com.atguigu.book.pojo;

/**
 * ClassName: User
 * Package: com.atguigu.book.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/8 21:17
 * @Version 1.0
 */

public class User {

   private Integer id;
   private String uname;
   private String pwd;
   private String email;
   private Integer role = 0;

   private Cart cart;

   public User() {
   }

   public Cart getCart() {
      return cart;
   }

   public void setCart(Cart cart) {
      this.cart = cart;
   }

   public User(Integer id) {
      this.id = id;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getUname() {
      return uname;
   }

   public void setUname(String uname) {
      this.uname = uname;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Integer getRole() {
      return role;
   }

   public void setRole(Integer role) {
      this.role = role;
   }
}
