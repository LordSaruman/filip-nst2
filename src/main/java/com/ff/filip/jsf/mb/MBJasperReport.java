/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author filip
 */
@Named(value = "mbJasper")
@SessionScoped
public class MBJasperReport implements Serializable{
    
    
    @PostConstruct
    public void init(){
        
    }
    
    public void printPDF(){
        
    }
}
