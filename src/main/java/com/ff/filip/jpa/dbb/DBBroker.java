/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jpa.dbb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.domen.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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

    public User checkUserPass(String username, String password) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
            EntityManager em = emf.createEntityManager();
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
        EntityManager em = emf.createEntityManager();

        User u = (User) em.createQuery("SELECT u FROM User u WHERE u.username = :user AND u.password = :pass").setParameter("user", user.getUsername()).setParameter("pass", user.getPassword()).getSingleResult();

        if (user == null) {
            throw new Exception("User doesn't exist");
        } else {
            return u;
        }
    }

    public List<User> findAllUsers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<User> listUsers = em.createQuery("SELECT u FROM User u").getResultList();
        em.close();
        emf.close();
        return listUsers;
    }

    public User findByUserId(int i) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        User u = em.find(User.class, (int) i);
        em.close();
        emf.close();
        return u;
    }

    public List<Mesto> findAllMesto() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<Mesto> list = em.createQuery("SELECT m FROM Mesto m").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public Mesto findMestoByPtt(int i) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Mesto m = em.find(Mesto.class, (int) i);
        em.close();
        emf.close();
        return m;
    }

    public Mesto findMestoByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Mesto> query = em.createNamedQuery("Mesto.findByNaziv", Mesto.class).setParameter("naziv", name);
        Mesto m = query.getSingleResult();
        em.close();
        emf.close();
        return m;
    }

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
        EntityManager em = emf.createEntityManager();

        Ispit ispit = em.find(Ispit.class, (int) i);
        em.close();
        emf.close();
        return ispit;
    }

    public Ispit findIspitByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Ispit> query = em.createNamedQuery("Ispit.findByNazivIspita", Ispit.class).setParameter("nazivIspita", name);
        Ispit ispit = query.getSingleResult();
        em.close();
        emf.close();
        return ispit;
    }

    public void persistIspit(Ispit ispit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(ispit);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deleteIspitById(Ispit ispit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Ispit i = em.find(Ispit.class, ispit.getSifraIspita());
        em.getTransaction().begin();
        em.remove(i);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void updateIspit(Ispit ispit) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Ispit i = em.find(Ispit.class, ispit.getSifraIspita());
        try {
            if (i != null) {
                em.getTransaction().begin();
                i = em.merge(ispit);
                em.getTransaction().commit();
            } else {
                throw new Exception("Ispit with that ID does not exist!");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<IspitniRok> findAllIspitniRok() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<IspitniRok> list = em.createQuery("SELECT i FROM IspitniRok i").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public IspitniRok findIspitniRokById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        IspitniRok ispitniRok = em.find(IspitniRok.class, (int) id);
        em.close();
        emf.close();
        return ispitniRok;
    }

    public IspitniRok findIspitniRokByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        TypedQuery<IspitniRok> query = em.createNamedQuery("IspitniRok.findByNazivIspitnogRoka", IspitniRok.class).setParameter("nazivIspitnogRoka", name);
        IspitniRok ispitniRok = query.getSingleResult();
        em.close();
        emf.close();
        return ispitniRok;
    }

    public Student findStudentById(String id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        TypedQuery<Student> query = em.createNamedQuery("Student.findByBrInd", Student.class).setParameter("brInd", id);
        Student student = query.getSingleResult();
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

    public void persistStudent(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deleteStudentById(Student student) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Student s = em.find(Student.class, student.getBrInd());
        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void updateStudent(Student student) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Student s = em.find(Student.class, student.getBrInd());
        try {
            if (s != null) {
                em.getTransaction().begin();
                s = em.merge(student);
                em.getTransaction().commit();
            } else {
                throw new Exception("Student with that ID does not exist!");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            em.close();
            emf.close();
        }
    }

    public List<Polaganje> findAllPolaganje() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        List<Polaganje> list = em.createQuery("SELECT p FROM Polaganje p").getResultList();
        em.close();
        emf.close();
        return list;
    }

    public void persistAllPolaganja(List<Polaganje> list) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        for (Polaganje polaganje : list) {
            Polaganje p = new Polaganje();
            p.setDatum(polaganje.getDatum());
            p.setIspit(polaganje.getIspit());
            p.setIspitniRok(polaganje.getIspitniRok());
            p.setOcena(polaganje.getOcena());
            p.setStudent(polaganje.getStudent());

            em.persist(p);
        }

        tx.commit();
        em.close();
        emf.close();
    }

    public void persistPolaganje(Polaganje polaganje) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Polaganje p = new Polaganje();
        p.setDatum(polaganje.getDatum());
        p.setIspit(polaganje.getIspit());
        p.setIspitniRok(polaganje.getIspitniRok());
        p.setOcena(polaganje.getOcena());
        p.setStudent(polaganje.getStudent());

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void deletePolaganjeById(Polaganje polaganje) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("nst_filip");
        EntityManager em = emf.createEntityManager();

        Polaganje p = em.find(Polaganje.class, polaganje.getIdPolaganje());
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
