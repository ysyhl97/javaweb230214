package com.atguigu.book.controller;

import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: CartController
 * Package: com.atguigu.book.controller
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 9:14
 * @Version 1.0
 */

public class CartController {

    private CartService cartService;


    public String addCart(Integer bookId, HttpSession session) {

        //添加书本到购物车
        //1.如果没有这本书,就添加
        //2.如果有这本书,就数量加1
        cartService.addCart(bookId, session);

        return "cart/cart";
    }

    public String cart(HttpSession session) {
        List<CartItem> cartItemList = cartService.getCartItemList(session);
        session.setAttribute("cartItemList", cartItemList);
        return "cart/cart";
    }
}
