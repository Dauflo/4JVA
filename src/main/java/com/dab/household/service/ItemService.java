package com.dab.household.service;

import com.dab.household.dao.ItemDao;
import com.dab.household.entity.Item;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ItemService {
    @EJB
    private ItemDao itemDao;

    public Item addItem(Item item) {
        return itemDao.addItem(item);
    }

    public List<Item> findItemsByTitle(String title) {
        return itemDao.findItemsByTitle(title);
    }

    public Item findItemById(Long id) {
        return itemDao.findItemById(id);
    }

    public List<Item> findAll() {
        return itemDao.findAll();
    }
}
