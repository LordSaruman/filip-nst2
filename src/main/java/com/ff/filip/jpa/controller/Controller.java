/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.controller;

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
    
    public List<User>findAllUsers(){
        return DBBroker.getInstance().findAllUsers();
    }
}
