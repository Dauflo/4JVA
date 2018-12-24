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
import java.util.Map;

@ManagedBean
@URLMapping(id = "update-password", pattern = "/auth/update/password", viewId = "/jsf/auth/updatePassword.jsf")
public class UpdatePasswordBean {
    @EJB
    private UserService userService;

    private User user;

    private String oldPassword;
    private String newPassword;
    private String confirmation;

    public UpdatePasswordBean() {
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public void updatePassword() {
        if (!oldPassword.isEmpty() && PasswordEncryption.checkPassword(oldPassword, user.getPassword())) {
            if (!newPassword.isEmpty() && !confirmation.isEmpty() && newPassword.equals(confirmation)) {
                user.setPassword(PasswordEncryption.encryption(newPassword));
                userService.updateUser(user);
                try {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect("/DabHousehold-1.0-SNAPSHOT/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your new password is empty or the confirmation does not match !"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect or empty password !"));
        }
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }
}
