/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.administration;

/**
 *
 * @author filip
 */
public enum ESIndex {

    POLAGANJE("polaganje"),
    STUDENT("student");

    private String[] types;

    ESIndex(String... types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }
}
