/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.domen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author filip
 */
@Entity
@Table(name = "polaganje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polaganje.findAll", query = "SELECT p FROM Polaganje p"),
    @NamedQuery(name = "Polaganje.findByBrInd", query = "SELECT p FROM Polaganje p WHERE p.student.brInd = :brInd"),
    @NamedQuery(name = "Polaganje.findByPolaganjeId", query = "SELECT p FROM Polaganje p WHERE p.idPolaganje = :idPolaganje"),
    @NamedQuery(name = "Polaganje.findBySifraIspita", query = "SELECT p FROM Polaganje p WHERE p.ispit.sifraIspita = :sifraIspita"),
    @NamedQuery(name = "Polaganje.findBySifraIspitnogRoka", query = "SELECT p FROM Polaganje p WHERE p.ispitniRok.sifraIspitnogRoka = :sifraIspitnogRoka"),
    @NamedQuery(name = "Polaganje.findByOcena", query = "SELECT p FROM Polaganje p WHERE p.ocena = :ocena"),
    @NamedQuery(name = "Polaganje.findByDatum", query = "SELECT p FROM Polaganje p WHERE p.datum = :datum")})
public class Polaganje implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PolaganjeId")
    private int idPolaganje;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BrInd")
    private Student student;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SifraIspitnogRoka")
    private IspitniRok ispitniRok;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SifraIspita")
    private Ispit ispit;

    @Column(name = "Ocena", unique = true, nullable = false)
    @NotNull(message = "Parametar ocena ne moze biti prazan")
    @Max(10)
    @Min(1)
    private int ocena;

    @Column(name = "Datum", unique = true, nullable = false)
    @NotNull(message = "Parametar datum ne moze biti prazan")
    @Past(message = "Ne moze se postavljati datum u buducnosti, moze samo u proslosti pa sve do sadasnjeg trenutka")
    @Temporal(TemporalType.DATE)
    private Date datum;

    public Polaganje() {
    }

    public Polaganje(int idPolaganje, Student student, IspitniRok ispitniRok, Ispit ispit, int ocena, Date datum) {
        this.idPolaganje = idPolaganje;
        this.student = student;
        this.ispitniRok = ispitniRok;
        this.ispit = ispit;
        this.ocena = ocena;
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getIdPolaganje() {
        return idPolaganje;
    }

    public void setIdPolaganje(int idPolaganje) {
        this.idPolaganje = idPolaganje;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public IspitniRok getIspitniRok() {
        return ispitniRok;
    }

    public void setIspitniRok(IspitniRok ispitniRok) {
        this.ispitniRok = ispitniRok;
    }

    public Ispit getIspit() {
        return ispit;
    }

    public void setIspit(Ispit ispit) {
        this.ispit = ispit;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
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
        final Polaganje other = (Polaganje) obj;
        if (this.idPolaganje != other.idPolaganje) {
            return false;
        }
        if (this.ocena != other.ocena) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.ispitniRok, other.ispitniRok)) {
            return false;
        }
        if (!Objects.equals(this.ispit, other.ispit)) {
            return false;
        }
        if (!Objects.equals(this.datum, other.datum)) {
            return false;
        }
        return true;
    }
}
