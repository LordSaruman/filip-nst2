/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.controller;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Mesto;
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

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    public User logInUser(User user)throws Exception{
        return DBBroker.getInstance().logInUser(user);
    }
    
    public List<User>findAllUsers(){
        return DBBroker.getInstance().findAllUsers();
    }

    public User findByUserId(int i) {
        return DBBroker.getInstance().findByUserId(i);
    }

    public List<Mesto> findAllMesto() {
        return DBBroker.getInstance().findAllMesto();
    }

    public Mesto findMestoByPtt(int i) {
        return DBBroker.getInstance().findMestoByPtt(i);
    }
    
    public Mesto findMestoByName(String name){
        return DBBroker.getInstance().findMestoByName(name);
    }

    public List<Ispit> findAllIspit() {
        return DBBroker.getInstance().findAllIspit();
    }

    public Ispit findIspitById(int i) {
        return DBBroker.getInstance().findIspitById(i);
    }

    public Ispit findIspitByName(String name) {
        return DBBroker.getInstance().findIspitByName(name);
    }
    
    public void persistIspit(Ispit ispit){
        DBBroker.getInstance().persistIspit(ispit);
    }
    
    public void deleteIspitById(Ispit ispit){
        DBBroker.getInstance().deleteIspitById(ispit);
    }
    
    public void updateIspit(Ispit ispit) throws Exception{
        DBBroker.getInstance().updateIspit(ispit);
    }

    public List<IspitniRok> findAllIspitniRok(){
        return DBBroker.getInstance().findAllIspitniRok();
    }
    
    public IspitniRok findIspitniRokById(int id){
        return DBBroker.getInstance().findIspitniRokById(id);
    }
    
    public IspitniRok findIspitniRokByName(String name){
        return DBBroker.getInstance().findIspitniRokByName(name);
    }
    
    public Student findStudentById(String id){
        return DBBroker.getInstance().findStudentById(id);
    }
    
    public List<Student> findAllStudent(){
        return DBBroker.getInstance().findAllStudent();
    }
    
    public void persistStudent(Student student){
        DBBroker.getInstance().persistStudent(student);
    }
    
    public void deleteStudentById(Student student){
        DBBroker.getInstance().deleteStudentById(student);
    }
    
    public void updateStudent(Student student) throws Exception{
        DBBroker.getInstance().updateStudent(student);
    }
}
