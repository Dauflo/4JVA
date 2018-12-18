package com.dab.household.beans;

import com.dab.household.entity.Cart;
import com.dab.household.entity.User;
import com.dab.household.service.CartService;
import com.dab.household.utils.Pager;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ManagedBean
@URLMapping(id = "oldCart", pattern = "/auth/history/#{id}", viewId = "/jsf/auth/oldCart.xhtml")
public class OldCartBean {
    @EJB
    private CartService cartService;

    private List<Cart> oldCarts;

    private List<Integer> pageList;

    private User user;

    private Long id;

    public OldCartBean() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.id = Long.parseLong(req.getParameter("id"));
    }

    public List<Object> getOldCarts() {
        oldCarts = cartService.getUserCarts(user);

        return Pager.getList(oldCarts, this.id);
    }

    public List<Integer> getPageList() {
        pageList = Pager.getPageList(oldCarts);

        return pageList;
    }
}
