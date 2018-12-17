package com.dab.household.beans;

import com.dab.household.entity.Item;
import com.dab.household.service.ItemService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@URLMapping(id = "itemDescription", pattern = "/description/#{id}", viewId = "/jsf/itemDescription.xhtml")
public class ItemDescription {

    @EJB
    private ItemService itemService;

    private Long id;

    public ItemDescription() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.id = Long.parseLong(req.getParameter("id"));
    }

    public Item getItem() {
        return itemService.findItemById(this.id);
    }
}
