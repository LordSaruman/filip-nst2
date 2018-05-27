/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.domen.User;
import com.ff.filip.jpa.controller.Controller;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        Ispit i = Controller.getInstance().findIspitById(6);
        IspitniRok ir = Controller.getInstance().findIspitniRokByName("Februar");
        Date datum = new Date();
        Polaganje p = new Polaganje(0, s, ir, i, 7, datum);
        Polaganje p1 = new Polaganje(0, s, ir, i, 10, datum);
        List<Polaganje>list = new ArrayList<>();
        list.add(p);
        list.add(p1);
        
        Controller.getInstance().persistPolaganja(list);
//
       

    }
}
