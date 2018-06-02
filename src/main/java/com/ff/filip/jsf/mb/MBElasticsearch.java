/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author filip
 */
//@Named(value = "mbElasticsearch")
//@SessionScoped
public class MBElasticsearch implements Serializable{
    
//    @Inject
//    private ElasticsearchSearchService ess;
    
    public void loadMesta(){
        findAllMesto();
    }

    private void findAllMesto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
