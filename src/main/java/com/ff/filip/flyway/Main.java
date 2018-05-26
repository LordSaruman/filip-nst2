/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.flyway;

/**
 *
 * @author filip
 */
public class Main {
    
    public static void main(String[] args) {
        //testtiranje flyway - radi
        App a = new App();
        a.migrate();
    }
}
