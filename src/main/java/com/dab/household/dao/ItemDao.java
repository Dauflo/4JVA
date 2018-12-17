package com.dab.household.dao;

import com.dab.household.entity.Item;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ItemDao {
    Item addItem(Item item);

    List<Item> findItemsByTitle(String title);

    Item findItemById(Long id);
}
