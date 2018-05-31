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
@Table(name = "mesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mesto.findAll", query = "SELECT m FROM Mesto m"),
    @NamedQuery(name = "Mesto.findById", query = "SELECT m FROM Mesto m WHERE m.Ptt = :ptt"),
    @NamedQuery(name = "Mesto.findByNaziv", query = "SELECT m FROM Mesto m WHERE m.naziv = :naziv")})
public class Mesto implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "Ptt", unique = true, nullable = false)
    private int Ptt;

    @Column(name = "Naziv", unique = true, nullable = false)
    @NotNull(message = "Parametar naziv mesta ne moze biti prazan")
    @Size(min = 1, max = 40, message = "Naziv mora imati izmedju 1-40 karaktera")
    private String naziv;

    public Mesto() {
    }

    public Mesto(int Ptt, String naziv) {
        this.Ptt = Ptt;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getPtt() {
        return Ptt;
    }

    public void setPtt(int Ptt) {
        this.Ptt = Ptt;
    }
}
