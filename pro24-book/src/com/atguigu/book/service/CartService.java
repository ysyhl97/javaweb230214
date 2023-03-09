package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: CartService
 * Package: com.atguigu.book.service
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 9:23
 * @Version 1.0
 */

public interface CartService {

    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    /**
     * 获取指定用户的购物车
     * @param user
     * @return
     */
    Cart getCart(User user);

}
