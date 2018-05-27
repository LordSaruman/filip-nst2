/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.domen;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author filip
 */
@Entity
@Table(name = "ispit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ispit.findAll", query = "SELECT i FROM Ispit i"),
    @NamedQuery(name = "Ispit.findBySifraIspita", query = "SELECT i FROM Ispit i WHERE i.sifraIspita = :sifraIspita"),
    @NamedQuery(name = "Ispit.findByNazivIspita", query = "SELECT i FROM Ispit i WHERE i.nazivIspita = :nazivIspita")})
public class Ispit implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "SifraIspita", unique = true, nullable = false)
    private int sifraIspita;

    @Column(name = "NazivIspita", unique = true, nullable = false)
    @NotNull(message = "Name of the exam cannot be empty")
    @Size(min = 1, max = 30)
    private String nazivIspita;

    public Ispit() {
    }

    public Ispit(int sifraIspita, String nazivIspita) {
        this.sifraIspita = sifraIspita;
        this.nazivIspita = nazivIspita;
    }

    public String getNazivIspita() {
        return nazivIspita;
    }

    public void setNazivIspita(String nazivIspita) {
        this.nazivIspita = nazivIspita;
    }

    public int getSifraIspita() {
        return sifraIspita;
    }

    public void setSifraIspita(int sifraIspita) {
        this.sifraIspita = sifraIspita;
    }

}
