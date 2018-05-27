/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jasper.controller;

import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.jpa.controller.Controller;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author filip
 */
public class JasperController {

    private static JasperController jasperController;

    private JasperController() {
    }

    public static JasperController getInstance() {
        if (jasperController == null) {
            jasperController = new JasperController();
        }
        return jasperController;
    }
    
    public List<Mesto> getAllMesta(){
        List<Mesto> list = new LinkedList<>();
        list = Controller.getInstance().findAllMesto();
        return list;
    }
    
    public List<Polaganje> getAllPolaganje(){
        List<Polaganje> list = new LinkedList<>();
        list = Controller.getInstance().findAllPolaganje();
        return list;
    }
}
