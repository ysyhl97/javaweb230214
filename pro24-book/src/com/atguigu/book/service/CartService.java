package com.atguigu.book.service;

import com.atguigu.book.pojo.CartItem;

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

   void addCart(Integer bookId, HttpSession session);

   List<CartItem> getCartItemList(HttpSession session);
}
