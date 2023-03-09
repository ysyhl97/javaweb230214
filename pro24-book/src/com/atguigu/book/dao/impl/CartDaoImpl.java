package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.CartDao;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
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
    public void insertCartItem(CartItem cartItem) {
        executeUpdate("insert into t_cart_item values (0,?,?,?)", cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUserBean().getId());
    }

    @Override
    public void deleteCartItem(Integer id) {

    }


    @Override
    public void updateCartItem(CartItem cartItem) {
        executeUpdate("update t_cart_item set buyCount = ? where book = ?", cartItem.getBuyCount(), cartItem.getBook().getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return executeQuery("select * from t_cart_item where userBean = ?", user.getId());
    }

}
