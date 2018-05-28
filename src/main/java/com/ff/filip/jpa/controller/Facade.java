/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.controller;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.domen.User;
import java.util.List;

/**
 *
 * @author filip
 */
public class Facade {
    
    public User checkUserPass(String username, String password) {
        return Controller.getInstance().checkUserPass(username, password);
    }

    public User logInUser(User user) throws Exception {
        return Controller.getInstance().logInUser(user);
    }

    public List<User> findAllUsers() {
        return Controller.getInstance().findAllUsers();
    }

    public User findByUserId(int i) {
        return Controller.getInstance().findByUserId(i);
    }

    public List<Mesto> findAllMesto() {
        return Controller.getInstance().findAllMesto();
    }

    public Mesto findMestoByPtt(int i) {
        return Controller.getInstance().findMestoByPtt(i);
    }

    public Mesto findMestoByName(String name) {
        return Controller.getInstance().findMestoByName(name);
    }

    public List<Ispit> findAllIspit() {
        return Controller.getInstance().findAllIspit();
    }

    public Ispit findIspitById(int i) {
        return Controller.getInstance().findIspitById(i);
    }

    public Ispit findIspitByName(String name) {
        return Controller.getInstance().findIspitByName(name);
    }

    public void persistIspit(Ispit ispit) {
        Controller.getInstance().persistIspit(ispit);
    }

    public void deleteIspitById(Ispit ispit) {
        Controller.getInstance().deleteIspitById(ispit);
    }

    public void updateIspit(Ispit ispit) throws Exception {
        Controller.getInstance().updateIspit(ispit);
    }

    public List<IspitniRok> findAllIspitniRok() {
        return Controller.getInstance().findAllIspitniRok();
    }

    public IspitniRok findIspitniRokById(int id) {
        return Controller.getInstance().findIspitniRokById(id);
    }

    public IspitniRok findIspitniRokByName(String name) {
        return Controller.getInstance().findIspitniRokByName(name);
    }

    public Student findStudentById(String id) {
        return Controller.getInstance().findStudentById(id);
    }

    public List<Student> findAllStudent() {
        return Controller.getInstance().findAllStudent();
    }

    public void persistStudent(Student student) {
        Controller.getInstance().persistStudent(student);
    }

    public void deleteStudentById(Student student) {
        Controller.getInstance().deleteStudentById(student);
    }

    public void updateStudent(Student student) throws Exception {
        Controller.getInstance().updateStudent(student);
    }

    public List<Polaganje> findAllPolaganje() {
        return Controller.getInstance().findAllPolaganje();
    }

    public void persistPolaganja(List<Polaganje> list) {
        Controller.getInstance().persistPolaganja(list);
    }

    public void persistPolaganje(Polaganje polaganje) {
        Controller.getInstance().persistPolaganje(polaganje);
    }

    public void deletePolaganjeById(Polaganje polaganje) {
        Controller.getInstance().deletePolaganjeById(polaganje);
    }
}
