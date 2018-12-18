package com.dab.household.dao.jpa;

import com.dab.household.dao.CartDao;
import com.dab.household.entity.Cart;
import com.dab.household.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JpaCartDao implements CartDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cart addCart(Cart cart) {
        em.persist(cart);
        return cart;
    }

    @Override
    public Cart getOneById(Long id) {
        return em.find(Cart.class, id);
    }

    @Override
    public List<Cart> getAllCarts() {
        List<Cart> carts;

        try {
            carts = em.createQuery("SELECT c FROM Cart c").getResultList();
        } catch (Exception e) {
            carts = new ArrayList<>();
        }
        return carts;
    }

    @Override
    public List<Cart> getUserCarts(User user) {
        List<Cart> carts;

        try {
            carts = em.createQuery("SELECT c from Cart c WHERE c.user = :user")
                    .setParameter("user", user)
                    .getResultList();
        } catch (Exception e) {
            carts = new ArrayList<>();
        }
        return carts;
    }
}
