/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Student;
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
public class StudentService implements Serializable {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    @Resource
    private javax.transaction.UserTransaction utx;

    public Student findStudentById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Student student = em.find(Student.class, (String)id);
        em.close();
        emf.close();
        return student;
    }

    public List<Student> findAllStudent() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<Student> list = em.createQuery("SELECT s FROM Student s").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public List<Mesto> findAllMesto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        em = emf.createEntityManager();

        List<Mesto> list = em.createQuery("SELECT m FROM Mesto m").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public void deleteStudentById(Student student) {
        Student target = student;
        try {
            System.out.println("delete() entity not managed: " + student);
            utx.begin();
            target = em.merge(student);
            em.remove(target);
            utx.commit();
            System.out.print("delete() this entity should now be deleted: " + (!em.contains(target)));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void persistStudent(Student student) {
        try {
            utx.begin();
            em.persist(student);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void persistEdit(Student studentForEditing) {
        try {
            utx.begin();

            em.createQuery("UPDATE Student s "
                    + "SET s.ime =?1, s.prezime =?2, s.mesto =?3 "
                    + "WHERE s.brInd =?4")
                    .setParameter(1, studentForEditing.getIme())
                    .setParameter(2, studentForEditing.getPrezime())
                    .setParameter(3, studentForEditing.getMesto())
                    .setParameter(4, studentForEditing.getBrInd())
                    .executeUpdate();

            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public boolean checkBrInd(Student student) {
        boolean flag = true; //postoji
        Student s = null;
        s = findStudentById(student.getBrInd());
        if (s == null) {
            return false;
        }
        
        return flag;
    }
}
