package com.dab.household.dao.jpa;

import com.dab.household.dao.OrderDao;
import com.dab.household.entity.UserOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JpaOrderDao implements OrderDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public UserOrder addOder(UserOrder userOrder) {
        em.persist(userOrder);
        return userOrder;
    }

    @Override
    public UserOrder findOneById(Long id) {
        return em.find(UserOrder.class, id);
    }

    @Override
    public List<UserOrder> getAllOrders() {
        List<UserOrder> userOrders;

        try {
            userOrders = em.createQuery("SELECT o FROM UserOrder o").getResultList();
        } catch (Exception e) {
            userOrders = new ArrayList<>();
        }
        return userOrders;
    }
}
