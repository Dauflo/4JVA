package com.dab.household.service;

import com.dab.household.dao.UserDao;
import com.dab.household.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService {

    @EJB
    private UserDao userDao;

    public User addUser(User user) {
        return userDao.addUser(user);
    }

    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public User updateUser(User user) {
        return userDao.updateUser(user);
    }
}
