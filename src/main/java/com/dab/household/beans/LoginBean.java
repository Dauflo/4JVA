package com.dab.household.beans;

import com.dab.household.entity.User;
import com.dab.household.service.UserService;
import com.dab.household.utils.PasswordEncryption;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@URLMapping(id = "login", pattern = "/login", viewId = "/jsf/login.xhtml")
public class LoginBean {
    @EJB
    private UserService userService;

    private User user;

    public LoginBean() {
        user = new User();
    }

    public void loginUser() {
        User u = userService.findUserByUsername(user.getUsername());
        if (u != null && PasswordEncryption.checkPassword(user.getPassword(), u.getPassword())) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("user", u);
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect Username and Password",
                    "Please enter correct username and Password"));
        }
    }

    public void logoutUser() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }
}
