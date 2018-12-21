package com.dab.household.beans;

import com.dab.household.entity.Cart;
import com.dab.household.entity.UserOrder;
import com.dab.household.service.CartService;
import com.dab.household.service.OrderService;
import com.dab.household.utils.Pager;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ManagedBean
@URLMapping(id = "oldCartDetail", pattern = "/auth/detail/#{cartId}/#{page}", viewId = "/jsf/auth/oldCartDetail.xhtml")
public class OldCartDetailBean {
    @EJB
    private CartService cartService;

    @EJB
    private OrderService orderService;

    private Cart cart;

    private Long cartId;

    private Long page;

    public OldCartDetailBean() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.cartId = Long.parseLong(req.getParameter("cartId"));
        this.page = Long.parseLong(req.getParameter("page"));
    }

    public Cart getCart() {
        cart = cartService.findOneById(cartId);
        return cart;
    }

    public List<Object> getOrders() {
        return Pager.getList(orderService.getFromCart(cart), this.page);
    }

    public List<Integer> getPager() {
        return Pager.getPageList(orderService.getFromCart(cart));
    }
}
