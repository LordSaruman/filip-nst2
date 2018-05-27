/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Student;
import com.ff.filip.domen.User;
import com.ff.filip.jpa.controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip
 */
public class Proba {

    public static void main(String[] args) {

        Student s = Controller.getInstance().findStudentById("123/123");
        
        s.setBrInd("321/321");
        s.setIme("wp");
        try {
            Controller.getInstance().updateStudent(s);
        } catch (Exception ex) {
            Logger.getLogger(Proba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
