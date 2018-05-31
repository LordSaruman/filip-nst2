/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.Ispit;
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
import javax.persistence.TypedQuery;

/**
 *
 * @author filip
 */
@Named
@RequestScoped
public class IspitService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;

    public List<Ispit> findAllIspit() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<Ispit> list = em.createQuery("SELECT i FROM Ispit i").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public Ispit findIspitById(int i) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        Ispit ispit = em.find(Ispit.class, (int) i);
        em.close();
        emf.close();
        return ispit;
    }

    public Ispit findIspitByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        TypedQuery<Ispit> query = em.createNamedQuery("Ispit.findByNazivIspita", Ispit.class).setParameter("nazivIspita", name);
        Ispit ispit = query.getSingleResult();
        em.close();
        emf.close();
        return ispit;
    }

    public void persistIspit(Ispit ispit) {
        try {
            utx.begin();
            em.persist(ispit);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        } 
    }

    public void deleteIspitById(Ispit ispit) {
        Ispit target = ispit;
        try {
            System.out.println("delete() entity not managed: " + ispit);
            utx.begin();
            target = em.merge(ispit);
            em.remove(target);
            utx.commit();
            System.out.print("delete() this entity should now be deleted: " + (!em.contains(target)));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void persistEdit(Ispit ispitForEditing) {
        try {
            utx.begin();
            em.createQuery("UPDATE Ispit i "
                    + "SET i.nazivIspita =?1 "
                    + "WHERE i.sifraIspita =?2")
                    .setParameter(1, ispitForEditing.getNazivIspita())
                    .setParameter(2, ispitForEditing.getSifraIspita())
                    .executeUpdate();

            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public boolean checkIspitPass(Ispit ispit) {
        boolean flag = true; //postoji
        Ispit i = null;
        i = findIspitById(ispit.getSifraIspita());
        if (i == null) {
            return false;
        }
        
        return flag;
    }

}
