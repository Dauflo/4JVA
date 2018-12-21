package com.dab.household.beans;

import com.dab.household.entity.Cart;
import com.dab.household.entity.Item;
import com.dab.household.entity.User;
import com.dab.household.entity.UserOrder;
import com.dab.household.service.CartService;
import com.dab.household.service.UserService;
import com.dab.household.utils.CartUtils;
import com.dab.household.utils.Pager;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@URLMapping(id = "myCart", pattern = "/auth/my-cart/#{pageId}", viewId = "/jsf/auth/myCart.xhtml")
public class MyCartBean {
    @EJB
    private UserService userService;

    @EJB
    private CartService cartService;

    private User user;
    private List<Item> itemList;

    private Cart cart;

    private Long pageId;

    public MyCartBean() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        itemList = (List<Item>) session.getAttribute("cart");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.pageId = Long.parseLong(req.getParameter("pageId"));

    }

    public User getUser() {
        return user;
    }

    public List<Object> getOrders() {
        cart = CartUtils.generateCart(itemList);
        return Pager.getList(cart.getUserOrders(), this.pageId);
    }

    public void removeToCart(UserOrder order) {
        itemList.remove(order.getItem());
    }

    public void buyMyCart() {
        cart.setUser(user);
        cartService.addCart(cart);
        cart = new Cart();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("cart", new ArrayList<>());
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("../my-cart/1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCartLenght() {
        return cart.getUserOrders().size();
    }

    public List<Integer> getPager() {
        return Pager.getPageList(cart.getUserOrders());
    }
}
