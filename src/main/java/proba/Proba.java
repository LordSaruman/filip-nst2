/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proba;

import com.ff.filip.domen.User;
import com.ff.filip.jpa.controller.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filip
 */
public class Proba {
    
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        
        list = Controller.getInstance().findAllUsers();
        for (User user : list) {
            System.out.println(user.getUserId());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        }
    }
}
