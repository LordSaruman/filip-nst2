/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.IspitniRok;
import java.io.Serializable;
import java.util.List;
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
public class IspitnirokService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;
    
    public List<IspitniRok> findAllIspitniRok() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        List<IspitniRok> list = em.createQuery("SELECT i FROM IspitniRok i").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public IspitniRok findIspitniRokById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        IspitniRok ispitniRok = em.find(IspitniRok.class, (int) id);
        em.close();
        emf.close();
        return ispitniRok;
    }

    public IspitniRok findIspitniRokByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        TypedQuery<IspitniRok> query = em.createNamedQuery("IspitniRok.findByNazivIspitnogRoka", IspitniRok.class).setParameter("nazivIspitnogRoka", name);
        IspitniRok ispitniRok = query.getSingleResult();
        em.close();
        emf.close();
        return ispitniRok;
    }
}
