package com.dab.household.beans;

import com.dab.household.entity.Item;
import com.dab.household.entity.User;
import com.dab.household.service.ItemService;
import com.dab.household.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@ManagedBean
@URLMapping(id = "searchItem", pattern = "/search/#{title}", viewId = "/jsf/searchItem.xhtml")
public class SearchItemBean {
    @EJB
    private ItemService itemService;

    @EJB
    private UserService userService;

    private List<Item> items;

    private String title;

    public SearchItemBean() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.title = req.getParameter("title");
    }

    public List<Item> getItems() {
        items = itemService.findItemsByTitle(this.title);
        return items;
    }

    public void addToChart(Long id) {
        // TODO if auth
        Item item = itemService.findItemById(id);

        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        user.addItem(item);
        userService.updateUser(user);

        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
