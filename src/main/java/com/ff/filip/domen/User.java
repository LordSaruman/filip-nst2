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
@Table(name = "user")
public class User {

    @Id
    @Column(name = "UserId", unique = true, nullable = false)
    private int UserId;

    @Column(name = "Username", unique = true, nullable = false)
    @Size(min = 1, max = 20)
    @NotNull(message = "Username cannot be null")
    private String username;

    @Column(name = "Password", unique = true, nullable = false)
    @NotNull(message = "Password cannot be null")
    @Size(min = 1, max = 20)
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
