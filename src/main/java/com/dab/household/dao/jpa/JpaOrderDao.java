package com.dab.household.dao.jpa;

import com.dab.household.dao.OrderDao;
import com.dab.household.entity.Order;

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
    public Order addOder(Order order) {
        em.persist(order);
        return order;
    }

    @Override
    public Order findOneById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders;

        try {
            orders = em.createQuery("SELECT o FROM Order o").getResultList();
        } catch (Exception e) {
            orders = new ArrayList<>();
        }
        return orders;
    }
}
