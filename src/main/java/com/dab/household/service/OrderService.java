package com.dab.household.service;

import com.dab.household.dao.OrderDao;
import com.dab.household.entity.UserOrder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OrderService {
    @EJB
    private OrderDao orderDao;

    public UserOrder addOrder(UserOrder userOrder) {
        return orderDao.addOder(userOrder);
    }

    public UserOrder findOneById(Long id) {
        return orderDao.findOneById(id);
    }

    public List<UserOrder> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
