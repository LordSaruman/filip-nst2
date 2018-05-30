/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Student;
import com.ff.filip.jpa.dbb.StudentService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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
    Student studentForDeleting;
    private List<Student> list;

    public MBStudent() {
        student = new Student();
        studentForDeleting = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Student getStudentForDeleting() {
        return studentForDeleting;
    }

    public void setStudentForDeleting(Student studentForDeleting) {
        this.studentForDeleting = studentForDeleting;
        System.out.println(studentForDeleting.getBrInd());
        System.out.println(studentForDeleting.getIme());
    }

    public void findAllStudent() {
        //list = Controller.getInstance().findAllStudent();
        list = ss.findAllStudent();
    }
    
    public void deleteStudent(){
        System.out.println(studentForDeleting.getBrInd());
        System.out.println(studentForDeleting.getIme());
        //Controller.getInstance().deleteStudentById(studentForDeleting);
        //mbsp.odradi(studentForDeleting);
        ss.deleteStudentById(studentForDeleting);
    }
}
