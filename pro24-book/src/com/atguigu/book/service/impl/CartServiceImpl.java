package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartDao;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: CartServiceImpl
 * Package: com.atguigu.book.service.impl
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 9:24
 * @Version 1.0
 */

public class CartServiceImpl implements CartService {

    private CartDao cartDao;
    private BookService bookService;

    @Override
    public void addCart(Integer bookId, HttpSession session) {
        // 从session中获取当前登录用户
        User currUser = (User) session.getAttribute("currUser");
        // 根据用户和bookId查询是否有这本书
        CartItem cartItem = cartDao.selectCartByBookId(new CartItem(new Book(bookId), currUser));
        // 没有就新增此书,数量为1
        if (cartItem == null) {
            cartDao.insertCart(new CartItem(new Book(bookId), 1, currUser));
        } else {
            // 否则数量加1
            cartDao.updateCartBuyCount(bookId);
        }

        //操作完成后,从数据库中重新查询该用户的所有cartItem(封装book对象)
        List<CartItem> cartItemList = getCartItemList(session);

        session.setAttribute("cartItemList", cartItemList);
    }

    /**
     * 查询当前用户的所有cartItem,封装book对象
     *
     * @return
     */
    @Override
    public List<CartItem> getCartItemList(HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");
        if (currUser != null) {
            List<CartItem> cartItemList = cartDao.selectCartByUserBean(currUser.getId());
            for (CartItem item : cartItemList) {
                Book book = bookService.getBookById(item.getBook().getId());
                item.setBook(book);
            }
            return cartItemList;
        }
        return null;
    }
}
