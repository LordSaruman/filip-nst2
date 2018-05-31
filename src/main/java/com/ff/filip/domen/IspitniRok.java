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
@Table(name = "ispitnirok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IspitniRok.findAll", query = "SELECT i FROM IspitniRok i"),
    @NamedQuery(name = "IspitniRok.findBySifraIspitnogRoka", query = "SELECT i FROM IspitniRok i WHERE i.sifraIspitnogRoka = :sifraIspitnogRoka"),
    @NamedQuery(name = "IspitniRok.findByNazivIspitnogRoka", query = "SELECT i FROM IspitniRok i WHERE i.nazivIspitnogRoka = :nazivIspitnogRoka")})
public class IspitniRok implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "SifraIspitnogRoka", unique = true, nullable = false)
    private int sifraIspitnogRoka;

    @Column(name = "NazivIspitnogRoka", unique = true, nullable = false)
    @NotNull(message = "Parametar naziv ispitnog roka ne moze biti prazan")
    @Size(min = 1, max = 20, message = "Naziv ispitnog roka mora imati izmedju 1-20 karaktera")
    private String nazivIspitnogRoka;

    public IspitniRok() {
    }

    public IspitniRok(int sifraIspitnogRoka, String nazivIspitnogRoka) {
        this.sifraIspitnogRoka = sifraIspitnogRoka;
        this.nazivIspitnogRoka = nazivIspitnogRoka;
    }

    public String getNazivIspitnogRoka() {
        return nazivIspitnogRoka;
    }

    public void setNazivIspitnogRoka(String nazivIspitnogRoka) {
        this.nazivIspitnogRoka = nazivIspitnogRoka;
    }

    public int getSifraIspitnogRoka() {
        return sifraIspitnogRoka;
    }

    public void setSifraIspitnogRoka(int sifraIspitnogRoka) {
        this.sifraIspitnogRoka = sifraIspitnogRoka;
    }
}
