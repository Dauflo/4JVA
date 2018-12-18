package com.dab.household.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity()
public class UserOrder extends BaseEntity implements Serializable {
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Item item;

    @NotNull
    private Long quantity;

    public UserOrder() {
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
