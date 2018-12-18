package com.dab.household.utils;

import com.dab.household.entity.Cart;
import com.dab.household.entity.Item;
import com.dab.household.entity.UserOrder;

import java.util.List;

public class CartUtils {
    public static Cart generateCart(List<Item> items) {
        Cart cart = new Cart();
        for (Item item : items) {
            if (cart.getUserOrders().size() == 0) {
                UserOrder userOrder = new UserOrder();
                userOrder.setItem(item);
                userOrder.setQuantity(1L);
                userOrder.setCart(cart);
                cart.addOrder(userOrder);
            } else {
                boolean found = false;
                List<UserOrder> userOrderList = cart.getUserOrders();
                for (UserOrder userOrder : userOrderList) {
                    if (userOrder.getItem().getId() == item.getId()) {
                        userOrder.setQuantity(userOrder.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    UserOrder userOrder = new UserOrder();
                    userOrder.setItem(item);
                    userOrder.setCart(cart);
                    userOrder.setQuantity(1L);
                    cart.addOrder(userOrder);
                }
            }
        }
        return cart;
    }
}
