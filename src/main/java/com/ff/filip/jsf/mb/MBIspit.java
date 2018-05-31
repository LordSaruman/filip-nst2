/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.jpa.dbb.IspitService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author filip
 */
@Named(value = "mbIspit")
@SessionScoped
public class MBIspit implements Serializable {

    @Inject
    private IspitService ispitService;

    private Ispit ispit;
    private Ispit ispitForEditing;
    private Ispit ispitForDeleting;
    private List<Ispit> listIspit;

    public MBIspit() {
        ispit = new Ispit();
        ispitForEditing = new Ispit();
        ispitForDeleting = new Ispit();
    }

    public Ispit getIspit() {
        return ispit;
    }

    public void setIspit(Ispit ispit) {
        this.ispit = ispit;
    }

    public Ispit getIspitForEditing() {
        return ispitForEditing;
    }

    public void setIspitForEditing(Ispit ispitForEditing) {
        this.ispitForEditing = ispitForEditing;
    }

    public Ispit getIspitForDeleting() {
        return ispitForDeleting;
    }

    public void setIspitForDeleting(Ispit ispitForDeleting) {
        this.ispitForDeleting = ispitForDeleting;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje! ", "  Ispit je postavljen za brisanje."));
    }

    public List<Ispit> getListIspit() {
        return listIspit;
    }

    public void setListIspit(List<Ispit> listIspit) {
        this.listIspit = listIspit;
    }

    public void findAllIspit() {
        listIspit = ispitService.findAllIspit();
    }

    public void deleteIspit() {
        ispitService.deleteIspitById(ispitForDeleting);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno obrisan."));
    }

    public void persistIspit() {
        boolean flag = checkIspitPass(ispit);

        if (flag == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje: ", "  Ispit nije sacuvan!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska: ", "  Sifra ispita vec postoji u bazi. Molimo izaberite drugu sifru."));
        } else {
            ispitService.persistIspit(ispit);
            ispit = new Ispit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno sacuvan."));
        }
    }

    public void persisEditStudent() {
        ispitService.persistEdit(ispitForEditing);
        ispitForEditing = new Ispit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno izmenjen i sacuvan."));
    }

    private boolean checkIspitPass(Ispit ispit) {
        boolean flag = false; //ne postoji sifra u bazi
        flag = ispitService.checkIspitPass(ispit);

        return flag;
    }

}
