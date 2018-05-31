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
    private List<Ispit> listIspit;

    public MBIspit() {
        ispit = new Ispit();
        ispitForEditing = new Ispit();
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

    public List<Ispit> getListIspit() {
        return listIspit;
    }

    public void setListIspit(List<Ispit> listIspit) {
        this.listIspit = listIspit;
    }

    public void loadIspite() {
        findAllIspit();
    }

    private void findAllIspit() {
        listIspit = ispitService.findAllIspit();
    }

    public void deleteIspit() {
        ispitService.deleteIspitById(ispit);
    }

    public void persistIspit() {
        ispitService.persistIspit(ispit);
        ispit = new Ispit();
    }

    public void persisEditStudent() {
        ispitService.persistEdit(ispitForEditing);
        ispitForEditing = new Ispit();
    }
}
