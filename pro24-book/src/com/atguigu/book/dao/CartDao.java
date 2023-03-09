package com.atguigu.book.dao;

import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

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

   void insertCartItem(CartItem cartItem);
   void deleteCartItem(Integer id);
   void updateCartItem(CartItem cartItem);

   /**
    * 查询指定用户的所有购物车项
    * @return
    */
   List<CartItem> getCartItemList(User user);

}
