package com.dab.household.beans;

import com.dab.household.entity.User;
import com.dab.household.service.UserService;
import com.dab.household.utils.PasswordEncryption;
import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean
@URLMapping(id = "register", pattern = "/register", viewId = "/jsf/register.xhtml")
public class RegisterBean {

    @EJB
    private UserService userService;

    private User user;

    private String passwordConfirmation;

    public RegisterBean() {
        // TODO send redirect if user already connected
        user = new User();
    }

    public void registerUser() {
        if (user.getPassword().equals(passwordConfirmation)) {
            user.setPassword(PasswordEncryption.encryption(user.getPassword()));
            userService.addUser(user);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .put("user", user);
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public User getUser() {
        return user;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
