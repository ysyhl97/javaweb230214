package com.atguigu.book.pojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: Cart
 * Package: com.atguigu.book.pojo
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/9 11:54
 * @Version 1.0
 */

public class Cart {
    // map中Integer为bookId
    private Map<Integer, CartItem> cartItemMap;
    // 总金额
    private Double totalMoney;
    // 总商品数
    private Integer totalCount;

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {

        if (cartItemMap != null && cartItemMap.size() > 0) {
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> entry : entries) {
                CartItem cartItem = entry.getValue();
                totalMoney = totalMoney + cartItem.getBuyCount() * cartItem.getBook().getPrice();
            }
        }

        return totalMoney;
    }


    public Integer getTotalCount() {
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }


    public Cart() {
    }
}
