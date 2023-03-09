package com.atguigu.book.dao;

import com.atguigu.book.pojo.CartItem;

import java.util.List;

/**
 * ClassName: CartDao
 * Package: com.atguigu.book.dao
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 9:25
 * @Version 1.0
 */

public interface CartDao {

   void insertCart(CartItem cartItem);
   void deleteCart(Integer id);

   CartItem selectCartByBookId(CartItem cartItem);

   void updateCartBuyCount(Integer bookId);

   List<CartItem> selectCartByUserBean(Integer userBean);
}
