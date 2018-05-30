/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.rest.service;

import com.ff.filip.domen.Student;
import com.ff.filip.jpa.controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author filip
 */
@Path("student")
public class StudentFacadeREST extends AbstractFacade<Student> {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    public StudentFacadeREST() {
        super(Student.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Student entity) {
        Controller.getInstance().persistStudent(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Student entity) {
        try {
            Controller.getInstance().updateStudent(entity);
        } catch (Exception ex) {
            Logger.getLogger(StudentFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        Student s = Controller.getInstance().findStudentById(id);
        Controller.getInstance().deleteStudentById(s);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student find(@PathParam("id") String id) {
        return Controller.getInstance().findStudentById(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findAll() {
        return Controller.getInstance().findAllStudent();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<Student> list = new ArrayList<>();
        list = Controller.getInstance().findAllStudent();
        
        return list.subList(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        List<Student> list = new ArrayList<>();
        list = Controller.getInstance().findAllStudent();
        int count = list.size();
        return String.valueOf(count);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
