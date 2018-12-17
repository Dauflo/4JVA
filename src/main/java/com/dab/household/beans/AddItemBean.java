package com.dab.household.beans;

import com.dab.household.entity.Item;
import com.dab.household.service.ItemService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean
@URLMapping(id = "addItem", pattern = "/add-item", viewId = "/jsf/addItem.xhtml")
public class AddItemBean {
    @EJB
    private ItemService itemService;

    private Item item;

    public AddItemBean() {
        item = new Item();
    }

    public void add() {
        itemService.addItem(item);
    }

    public Item getItem() {
        return item;
    }
}
