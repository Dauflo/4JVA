package com.dab.household.beans;

import com.dab.household.entity.Cart;
import com.dab.household.entity.Item;
import com.dab.household.entity.User;
import com.dab.household.service.CartService;
import com.dab.household.service.UserService;
import com.dab.household.utils.CartUtils;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@URLMapping(id = "myCart", pattern = "/auth/my-cart", viewId = "/jsf/auth/myCart.xhtml")
public class MyCartBean {
    @EJB
    private UserService userService;

    @EJB
    private CartService cartService;

    private User user;
    private List<Item> itemList;

    private Cart cart;

    public MyCartBean() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        itemList = (List<Item>) session.getAttribute("cart");
    }

    public User getUser() {
        return user;
    }

    public Cart getCart() {
        cart = CartUtils.generateCart(itemList);
        return cart;
    }

    public void removeToCart(Item item) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buyMyCart() {
        cart.setUser(user);
        cartService.addCart(cart);
        cart = new Cart();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("cart", new ArrayList<>());
    }
}
