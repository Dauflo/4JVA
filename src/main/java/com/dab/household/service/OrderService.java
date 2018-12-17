package com.dab.household.service;

import com.dab.household.dao.OrderDao;
import com.dab.household.entity.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderService {
    @EJB
    private OrderDao orderDao;

    public Order addOrder(Order order) {
        return orderDao.addOder(order);
    }

    public Order findOneById(Long id) {
        return orderDao.findOneById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
