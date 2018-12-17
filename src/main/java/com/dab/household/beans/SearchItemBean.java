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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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

    public void addToCart(Item item) {
        User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        List<Item> cart = (List<Item>) session.getAttribute("cart");

        boolean loggedIn = user != null;
        if (loggedIn) {
            cart.add(item);
//            try {
//                FacesContext.getCurrentInstance().getExternalContext()
//                        .redirect("");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            try {
                response.sendRedirect("../login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
