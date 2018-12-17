package com.dab.household.utils;

import com.dab.household.entity.Cart;
import com.dab.household.entity.Item;
import com.dab.household.entity.Order;

import java.util.List;

public class CartUtils {
    public static Cart generateCart(List<Item> items) {
        Cart cart = new Cart();
        for (Item item : items) {
            if (cart.getOrders().size() == 0) {
                Order order = new Order();
                order.setItem(item);
                order.setQuantity(1);
                cart.addOrder(order);
            } else {
                boolean found = false;
                List<Order> orderList = cart.getOrders();
                for (Order order : orderList) {
                    if (order.getItem().getId() == item.getId()) {
                        order.setQuantity(order.getQuantity() + 1);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    Order order = new Order();
                    order.setItem(item);
                    order.setQuantity(1);
                    cart.addOrder(order);
                }
            }
        }
        return cart;
    }
}
