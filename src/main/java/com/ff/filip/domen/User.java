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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.UserId = :userId"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "UserId", unique = true, nullable = false)
    private int UserId;

    @Column(name = "Username", unique = true, nullable = false)
    @Size(min = 1, max = 20, message = "Username mora imati izmedju 1-20 karaktera")
    @NotNull(message = "Username ne moze biti prazan")
    private String username;

    @Column(name = "Password", unique = true, nullable = false)
    @NotNull(message = "Password ne moze biti prazan")
    @Size(min = 1, max = 20, message = "Username mora imati izmedju 1-20 karaktera")
    private String password;

    public User() {
    }

    public User(int UserId, String username, String password) {
        this.UserId = UserId;
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
