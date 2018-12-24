package com.dab.household.beans;

import com.dab.household.entity.Item;
import com.dab.household.service.CartService;
import com.dab.household.service.ItemService;
import com.dab.household.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@URLMapping(id = "index", pattern = "/", viewId = "index.xhtml")
public class IndexBean {
    @EJB
    private ItemService itemService;

    @EJB
    private UserService userService;

    @EJB
    private CartService cartService;

    public List<Item> getLastTenItems() {
        List<Item> items = itemService.findAll();
        List<Item> displayedItems = new ArrayList<>();

        if (items.size() <= 10) {
            return items;
        } else {
            for (int i = items.size() - 1; i >= items.size() - 10; i--) {
                displayedItems.add(items.get(i));
            }
            return displayedItems;
        }
    }

    public int getNumberOfItems() {
        return itemService.findAll().size();
    }

    public int getNumberOfUsers() {
        return userService.findAll().size();
    }

    public int getNumberOfCart() {
        return cartService.getAllCarts().size();
    }
}
