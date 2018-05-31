/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.User;
import com.ff.filip.jpa.dbb.UserService;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author filip
 */
@Named(value = "mbLogin")
@SessionScoped
public class MBLoginToTheSystem implements Serializable {

    @Inject
    UserService us;

    User loginuser;

    public MBLoginToTheSystem() {
        loginuser = new User();
        loginuser.setUsername("");
        loginuser.setPassword("");
    }

    public User getLoginuser() {
        return loginuser;
    }

    public void setLoginuser(User loginuser) {
        this.loginuser = loginuser;
    }

    public String loginTheUser() {
        System.out.println("USAO U METODU - Login");
        if (loginuser.getUsername() == null || loginuser.getPassword() == null || loginuser.getUsername().isEmpty() || loginuser.getPassword().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Niste popunili username ili password", null));
            return "";
        }

        try {
            System.out.println("Korinik: korisnicko ime:" + loginuser.getUsername() + ", kosrisnicka sifra:" + loginuser.getPassword());
            loginuser = us.logInUser(loginuser);
//            loginuser = dbb.logInUser(loginuser);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Korisnik je uspesno prijavljen", null));
            return "homepage.xhtml";
        } catch (Exception ex) {
            Logger.getLogger(MBLoginToTheSystem.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne moze da pronadje korisnika na osnovu zadatih vrednosti", null));
        }

        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.xhtml?faces-redirect=true";
    }
}
