package com.atguigu.book.service.impl;

import com.atguigu.book.dao.CartDao;
import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.BookService;
import com.atguigu.book.service.CartService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   /*
    此处太麻烦,数据库操作过多
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
*/

    /**
     * 查询当前用户的所有cartItem,封装book对象
     *
     * @return
     */
   /* @Override
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
    }*/
    @Override
    public void addCartItem(CartItem cartItem) {
        cartDao.insertCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartDao.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //判断当前用户的购物车中是否有这本书,有执行update,没有add
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }

            // 判断cartItemMap中的key(bookId)是否包含当前购物车项中的bookId
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                // 若包含则获取这本书对应的购物车项
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                // 将购物车项里的书数量+1
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                // 执行修改方法
                updateCartItem(cartItemTemp);
            } else {
                // 如果不包含这本书,则添加一条购物车项
                addCartItem(cartItem);
            }


        }
    }

    @Override
    public Cart getCart(User user) {
        // 查询指定用户的所有购物车项
        List<CartItem> cartItemList = cartDao.getCartItemList(user);
        // 创建map用来存放cartItem,key为bookId,value为对应的cartItem
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);

        return cart;
    }
}
