package com.dab.household.dao;

import com.dab.household.entity.User;

import javax.ejb.Local;

@Local
public interface UserDao {
    User addUser(User user);

    User findUserByUsername(String username);

    User updateUser(User user);
}
