package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.CartDao;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * ClassName: CartDaoImpl
 * Package: com.atguigu.book.dao.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 9:29
 * @Version 1.0
 */

public class CartDaoImpl extends BaseDAO<CartItem> implements CartDao {
   @Override
   public void insertCart(CartItem cartItem) {
      executeUpdate("insert into t_cart_item values (0,?,?,?)",cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUserBean().getId());
   }

   @Override
   public void deleteCart(Integer id) {

   }

   @Override
   public CartItem selectCartByBookId(CartItem cartItem) {
      return load("select * from t_cart_item where book=? and userBean=?",cartItem.getBook().getId(),cartItem.getUserBean().getId());
   }

   @Override
   public void updateCartBuyCount(Integer bookId) {
      executeUpdate("update t_cart_item set buyCount = buyCount + 1 where book = ?",bookId);
   }

   @Override
   public List<CartItem> selectCartByUserBean(Integer userBean) {
      return executeQuery("select * from t_cart_item where userBean = ?",userBean);
   }
}
