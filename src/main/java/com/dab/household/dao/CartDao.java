package com.dab.household.dao;

import com.dab.household.entity.Cart;
import com.dab.household.entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartDao {
    Cart addCart(Cart cart);

    Cart getOneById(Long id);

    List<Cart> getAllCarts();

    List<Cart> getUserCarts(User user);
}
