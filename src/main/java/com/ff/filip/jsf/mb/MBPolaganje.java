/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.jpa.dbb.IspitService;
import com.ff.filip.jpa.dbb.IspitnirokService;
import com.ff.filip.jpa.dbb.PolaganjeService;
import com.ff.filip.jpa.dbb.StudentService;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@Named(value = "mbPolaganje")
@SessionScoped
public class MBPolaganje implements Serializable {

    @Inject
    private PolaganjeService polaganjeService;

    @Inject
    private StudentService studentService;

    @Inject
    private IspitService ispitService;

    @Inject
    private IspitnirokService ispitnirokService;

    private Polaganje polaganje;
    private List<Polaganje> listPolaganje;
    private List<Student> listStudent;
    private List<Ispit> listIspit;
    private List<IspitniRok> listIspitniRok;
    private Date currentDate;

    public MBPolaganje() {
        polaganje = new Polaganje();
        currentDate = new Date();
    }

    public Polaganje getPolaganje() {
        return polaganje;
    }

    public void setPolaganje(Polaganje polaganje) {
        this.polaganje = polaganje;
    }

    public List<Polaganje> getListPolaganje() {
        return listPolaganje;
    }

    public void setListPolaganje(List<Polaganje> listPolaganje) {
        this.listPolaganje = listPolaganje;
    }

    public List<Student> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<Student> listStudent) {
        this.listStudent = listStudent;
    }

    public List<Ispit> getListIspit() {
        return listIspit;
    }

    public void setListIspit(List<Ispit> listIspit) {
        this.listIspit = listIspit;
    }

    public List<IspitniRok> getListIspitniRok() {
        return listIspitniRok;
    }

    public void setListIspitniRok(List<IspitniRok> listIspitniRok) {
        this.listIspitniRok = listIspitniRok;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void loadLists() {
        findAllIspit();
        findAllIspitnirok();
        findAllStudents();
    }

    private void findAllIspit() {
        listIspit = ispitService.findAllIspit();
    }

    private void findAllIspitnirok() {
        listIspitniRok = ispitnirokService.findAllIspitniRok();
    }

    private void findAllStudents() {
        listStudent = studentService.findAllStudent();
    }

    public void persistPolaganje() {
        polaganjeService.persistPolaganje(polaganje);
        polaganje = new Polaganje();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Polaganje je uspesno sacuvano."));
    }
}
