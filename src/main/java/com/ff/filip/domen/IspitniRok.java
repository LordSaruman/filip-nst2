/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.domen;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author filip
 */
@Entity
@Table(name = "ispitnirok")
public class IspitniRok {

    @Id
    @Column(name = "SifraIspitnogRoka", unique = true, nullable = false)
    private int sifraIspitnogRoka;

    @Column(name = "NazivIspitnogRoka", unique = true, nullable = false)
    @NotNull(message = "Name of the exam week cannot be null")
    @Size(min = 1, max = 20)
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
