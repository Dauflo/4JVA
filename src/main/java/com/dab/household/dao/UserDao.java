package com.dab.household.dao;

import com.dab.household.entity.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserDao {
    User addUser(User user);

    User findUserByUsername(String username);

    User updateUser(User user);

    List<User> findAll();
}
