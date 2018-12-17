package com.dab.household.dao;

import com.dab.household.entity.Order;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderDao {
    Order addOder(Order order);

    Order findOneById(Long id);

    List<Order> getAllOrders();
}
