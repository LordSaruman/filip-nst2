/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.flyway;

import org.flywaydb.core.Flyway;

/**
 *
 * @author filip
 */
public class App {

    public void migrate() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost:3306/probaflyway", "root", "root");
        flyway.setLocations("filesystem:/home/filip/Desktop/flyway-novo/src/main/java/migration/");
        flyway.migrate();
    }
}
