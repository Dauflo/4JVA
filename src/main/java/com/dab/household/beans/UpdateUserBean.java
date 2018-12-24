package com.dab.household.beans;

import com.dab.household.entity.User;
import com.dab.household.service.UserService;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@URLMapping(id = "update", pattern = "/auth/update", viewId = "/jsf/auth/update.xhtml")
public class UpdateUserBean {

    @EJB
    private UserService userService;

    private User user;

    public UpdateUserBean() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public void update() {
        userService.updateUser(user);
    }

    public User getUser() {
        return user;
    }

    public void updatePassword() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("update/password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
