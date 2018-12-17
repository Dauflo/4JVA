package com.dab.household.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends BaseEntity implements Serializable {

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<UserOrder> userOrders;

    public Cart() {
        userOrders = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    public void addOrder(UserOrder userOrder) {
        this.userOrders.add(userOrder);
    }
}
