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
import com.ff.filip.jpa.dbb.DBBroker;
import java.util.List;

/**
 *
 * @author filip
 */
public class Controller {

    private static Controller controller;
    private static DBBroker dbb;

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
            dbb = new DBBroker();
        }
        return controller;
    }

    public User checkUserPass(String username, String password) {
        return dbb.checkUserPass(username, password);
    }

    public User logInUser(User user) throws Exception {
        return dbb.logInUser(user);
    }

    public List<User> findAllUsers() {
        return dbb.findAllUsers();
    }

    public User findByUserId(int i) {
        return dbb.findByUserId(i);
    }

    public List<Mesto> findAllMesto() {
        return dbb.findAllMesto();
    }

    public Mesto findMestoByPtt(int i) {
        return dbb.findMestoByPtt(i);
    }

    public Mesto findMestoByName(String name) {
        return dbb.findMestoByName(name);
    }

    public void persistMesto(Mesto mesto) {
        dbb.peristMesto(mesto);
    }

    public void deleteMestoById(Mesto mesto) {
        dbb.deleteMestoById(mesto);
    }

    public void updateMesto(Mesto mesto) throws Exception {
        dbb.updateMesto(mesto);
    }

    public List<Ispit> findAllIspit() {
        return dbb.findAllIspit();
    }

    public Ispit findIspitById(int i) {
        return dbb.findIspitById(i);
    }

    public Ispit findIspitByName(String name) {
        return dbb.findIspitByName(name);
    }

    public void persistIspit(Ispit ispit) {
        dbb.persistIspit(ispit);
    }

    public void deleteIspitById(Ispit ispit) {
        dbb.deleteIspitById(ispit);
    }

    public void updateIspit(Ispit ispit) throws Exception {
        dbb.updateIspit(ispit);
    }

    public List<IspitniRok> findAllIspitniRok() {
        return dbb.findAllIspitniRok();
    }

    public IspitniRok findIspitniRokById(int id) {
        return dbb.findIspitniRokById(id);
    }

    public IspitniRok findIspitniRokByName(String name) {
        return dbb.findIspitniRokByName(name);
    }

    public Student findStudentById(String id) {
        return dbb.findStudentById(id);
    }

    public List<Student> findAllStudent() {
        return dbb.findAllStudent();
    }

    public void persistStudent(Student student) {
        dbb.persistStudent(student);
    }

    public void deleteStudentById(Student student) {
        dbb.deleteStudentById(student);
    }

    public void updateStudent(Student student) throws Exception {
        dbb.updateStudent(student);
    }

    public List<Polaganje> findAllPolaganje() {
        return dbb.findAllPolaganje();
    }

    public void persistPolaganja(List<Polaganje> list) {
        dbb.persistAllPolaganja(list);
    }

    public void persistPolaganje(Polaganje polaganje) {
        dbb.persistPolaganje(polaganje);
    }

    public void deletePolaganjeById(Polaganje polaganje) {
        dbb.deletePolaganjeById(polaganje);
    }
}
