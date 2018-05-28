/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.domen;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author filip
 */
@Entity
@Table(name = "student")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByBrInd", query = "SELECT s FROM Student s WHERE s.brInd = :brInd"),
    @NamedQuery(name = "Student.findByIme", query = "SELECT s FROM Student s WHERE s.ime = :ime"),
    @NamedQuery(name = "Student.findByPrezime", query = "SELECT s FROM Student s WHERE s.prezime = :prezime")})
public class Student implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "BrInd", unique = true, nullable = false)
    private String brInd;

    @Column(name = "Ime", unique = true, nullable = false)
    @Size(min = 1, max = 30)
    private String ime;

    @Column(name = "Prezime", unique = true, nullable = false)
    @Size(min = 1, max = 30)
    private String prezime;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Ptt")
    private Mesto mesto;

    public Student() {
    }

    public Student(String brInd, String ime, String prezime, Mesto mesto) {
        this.brInd = brInd;
        this.ime = ime;
        this.prezime = prezime;
        this.mesto = mesto;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public String getBrInd() {
        return brInd;
    }

    public void setBrInd(String brInd) {
        this.brInd = brInd;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.brInd, other.brInd)) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.mesto, other.mesto)) {
            return false;
        }
        return true;
    }
}
