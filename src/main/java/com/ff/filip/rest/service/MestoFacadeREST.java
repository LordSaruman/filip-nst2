/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.rest.service;

import com.ff.filip.domen.Mesto;
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
@Path("mesto")
public class MestoFacadeREST extends AbstractFacade<Mesto> {

    @PersistenceContext(unitName = "nst_filip")
    private EntityManager em;

    public MestoFacadeREST() {
        super(Mesto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Mesto entity) {
        Controller.getInstance().persistMesto(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Mesto entity) {
        try {
            Controller.getInstance().updateMesto(entity);
        } catch (Exception ex) {
            Logger.getLogger(IspitFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        Mesto m = Controller.getInstance().findMestoByPtt(id);
        Controller.getInstance().deleteMestoById(m);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Mesto find(@PathParam("id") Integer id) {
        return Controller.getInstance().findMestoByPtt(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mesto> findAll() {
        return Controller.getInstance().findAllMesto();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mesto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        List<Mesto> list = new ArrayList<>();
        list = Controller.getInstance().findAllMesto();

        return list.subList(from, to);
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        List<Mesto> list = new ArrayList<>();
        list = Controller.getInstance().findAllMesto();

        int count = list.size();
        return String.valueOf(count);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
