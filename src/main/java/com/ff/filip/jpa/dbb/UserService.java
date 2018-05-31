/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.User;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author filip
 */
@Named
@RequestScoped
public class UserService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;
    
    public User checkUserPass(String username, String password) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
            em = emf.createEntityManager();
            User userDB = null;

            userDB = (User) em.createQuery("SELECT u FROM User u WHERE u.username = :user AND u.password = :pass")
                    .setParameter("user", username).getSingleResult();

            if (userDB.getPassword().equals(password)) {
                return userDB;
            }
        } catch (Exception e) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public User logInUser(User user) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        User u = (User) em.createQuery("SELECT u FROM User u WHERE u.username = :user AND u.password = :pass").setParameter("user", user.getUsername()).setParameter("pass", user.getPassword()).getSingleResult();

        if (user == null) {
            throw new Exception("User doesn't exist");
        } else {
            return u;
        }
    }

    public List<User> findAllUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        List<User> listUsers = em.createQuery("SELECT u FROM User u").getResultList();
        em.close();
        emf.close();
        return listUsers;
    }

    public User findByUserId(int i) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        User u = em.find(User.class, (int) i);
        em.close();
        emf.close();
        return u;
    }
}
