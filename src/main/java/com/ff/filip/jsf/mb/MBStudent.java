/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Student;
import com.ff.filip.jpa.dbb.StudentService;
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
@Named(value = "mbStudent")
@SessionScoped
public class MBStudent implements Serializable {

    @Inject
    private StudentService ss;

    private Student student;
    private Student studentForEditing;
    private Student studentForDeleting;
    private List<Student> list;
    private List<Mesto> listMesto;
    private boolean isInitialized = false;

    public MBStudent() {
        student = new Student();
        studentForDeleting = new Student();
        studentForEditing = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudentForEditing() {
        return studentForEditing;
    }

    public void setStudentForEditing(Student studentForEditing) {
        this.studentForEditing = studentForEditing;
    }

    public Student getStudentForDeleting() {
        return studentForDeleting;
    }

    public void setStudentForDeleting(Student studentForDeleting) {
        this.studentForDeleting = studentForDeleting;
        isInitialized = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje! ", "  Student je postavljen za brisanje."));
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public List<Mesto> getListMesto() {
        return listMesto;
    }

    public void setListMesto(List<Mesto> listMesto) {
        this.listMesto = listMesto;
    }

    public void findAllStudent() {
        list = ss.findAllStudent();
    }

    public void findAllMesto() {
        listMesto = ss.findAllMesto();
    }

    public void deleteStudent() {
        if (isInitialized) {
            ss.deleteStudentById(studentForDeleting);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno obrisan."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje!: ", "  Niste postavili studenta za brisanje"));
        }
    }

    public void persistStudent() {
        boolean flag = checkBrInd(student);

        if (flag == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje: ", "  Student nije sacuvan!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska: ", "  Broj indeksa vec postoji u bazi. Molimo izaberite drugi broj indeksa."));
        } else {
            ss.persistStudent(student);
            student = new Student();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno sacuvan."));
        }
    }

    public void persistEditStudent() {
        ss.persistEdit(studentForEditing);
        studentForEditing = new Student();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno izmenjen i sacuvan."));
    }

    public void loadMesta() {
        findAllMesto();
    }

    private boolean checkBrInd(Student student) {
        boolean flag = false; //br indeksa ne postoji u bazi
        flag = ss.checkBrInd(student);

        return flag;
    }
}
