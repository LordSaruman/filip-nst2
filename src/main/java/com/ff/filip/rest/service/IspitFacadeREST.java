/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.rest.service;

import com.ff.filip.domen.Ispit;
import com.ff.filip.jpa.controller.Controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("ispit")
public class IspitFacadeREST extends AbstractFacade<Ispit> {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    public IspitFacadeREST() {
        super(Ispit.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Ispit entity) {
        Controller.getInstance().persistIspit(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Ispit entity) {
        try {
            Controller.getInstance().updateIspit(entity);
        } catch (Exception ex) {
            Logger.getLogger(IspitFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        Ispit i = Controller.getInstance().findIspitById(id);
        Controller.getInstance().deleteIspitById(i);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Ispit find(@PathParam("id") Integer id) {
        return Controller.getInstance().findIspitById(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ispit> findAll() {
        return Controller.getInstance().findAllIspit();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ispit> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<Ispit> list = new ArrayList<>();
        list = Controller.getInstance().findAllIspit();
        
        return list.subList(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        List<Ispit> list = new ArrayList<>();
        list = Controller.getInstance().findAllIspit();
        int count = list.size();
        return String.valueOf(count);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
