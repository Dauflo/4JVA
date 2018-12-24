package com.dab.household.dao.jpa;

import com.dab.household.dao.UserDao;
import com.dab.household.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class JpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User u;

        try {
            u = (User) em.createQuery("SELECT u FROM User u WHERE u.username = :username").setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            u = null;
        }
        return u;
    }

    @Override
    public User updateUser(User user) {
        em.merge(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> items;

        try {
            items = em.createQuery("SELECT u FROM User u").getResultList();
        } catch (Exception e) {
            items = new ArrayList<>();
        }

        return items;
    }
}
