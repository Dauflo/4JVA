package com.dab.household.service;

import com.dab.household.dao.CartDao;
import com.dab.household.entity.Cart;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CartService {
    @EJB
    private CartDao cartDao;

    public Cart addCart(Cart cart) {
        return cartDao.addCart(cart);
    }

    public Cart findOneById(Long id) {
        return cartDao.getOneById(id);
    }

    public List<Cart> getAllCarts() {
        return cartDao.getAllCarts();
    }
}
