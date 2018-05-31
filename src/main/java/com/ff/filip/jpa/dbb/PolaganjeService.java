/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.Polaganje;
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
public class PolaganjeService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;

    public List<Polaganje> findAllPolaganje() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        List<Polaganje> list = em.createQuery("SELECT p FROM Polaganje p").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public void persistAllPolaganja(List<Polaganje> list) {

        try {
            utx.begin();

            for (Polaganje polaganje : list) {
                Polaganje p = new Polaganje();
                p.setDatum(polaganje.getDatum());
                p.setIspit(polaganje.getIspit());
                p.setIspitniRok(polaganje.getIspitniRok());
                p.setOcena(polaganje.getOcena());
                p.setStudent(polaganje.getStudent());

                em.persist(p);
            }
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void persistPolaganje(Polaganje polaganje) {
        try {
            utx.begin();

            Polaganje p = new Polaganje();
            p.setDatum(polaganje.getDatum());
            p.setIspit(polaganje.getIspit());
            p.setIspitniRok(polaganje.getIspitniRok());
            p.setOcena(polaganje.getOcena());
            p.setStudent(polaganje.getStudent());

            em.persist(p);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void deletePolaganjeById(Polaganje polaganje) {
        Polaganje target = polaganje;
        try {
            System.out.println("delete() entity not managed: " + polaganje);
            utx.begin();
            target = em.merge(polaganje);
            em.remove(target);
            utx.commit();
            System.out.print("delete() this entity should now be deleted: " + (!em.contains(target)));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
