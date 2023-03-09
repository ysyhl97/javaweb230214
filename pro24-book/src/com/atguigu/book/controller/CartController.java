package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
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
        //从session中获取cart
        User currUser = (User) session.getAttribute("currUser");
        //将bookId封装成cartItem对象
        CartItem cartItem = new CartItem(new Book(bookId), 1, currUser);

        // 为什么要传这两个参数
        // cartItem 是为了执行add操作
        // currUser.getCart()  cart对象为了它的CartItemMap<Integer,CartItem>判断book是否添加
        cartService.addOrUpdateCartItem(cartItem, currUser.getCart());

        return "cart/cart";
    }

    /*public String cart(HttpSession session) {
        List<CartItem> cartItemList = cartService.getCartItemList(session);
        session.setAttribute("cartItemList", cartItemList);
        return "cart/cart";
    }*/
}
