/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author filip
 */
public class DBBroker {

    private static DBBroker dbb;

    public static DBBroker getInstance() {
        if (dbb == null) {
            dbb = new DBBroker();
        }
        return dbb;
    }

    public List<User> findAllUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();
        
        List<User> listUsers = em.createQuery("SELECT u FROM User u").getResultList();
        return listUsers;
    }
}
