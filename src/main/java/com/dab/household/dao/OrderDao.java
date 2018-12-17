package com.dab.household.dao;

import com.dab.household.entity.UserOrder;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderDao {
    UserOrder addOder(UserOrder userOrder);

    UserOrder findOneById(Long id);

    List<UserOrder> getAllOrders();
}
