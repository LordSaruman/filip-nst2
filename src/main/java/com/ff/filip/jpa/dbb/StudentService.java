/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.Student;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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
public class StudentService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;

    public List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<Student> list = em.createQuery("SELECT s FROM Student s").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public void deleteStudentById(Student student) {

        Student target = student;
        try {
            System.out.println(student.getBrInd());
            System.out.println(student.getIme());
            
            System.out.println("delete() entity not managed: " + student);
            utx.begin();
            target = em.merge(student);
            em.remove(target);
            utx.commit();
            System.out.print("delete() this entity should now be deleted: " + (!em.contains(target)) );
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

}
