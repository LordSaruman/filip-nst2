/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.elasticsearch.administration;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.Student;
import com.ff.filip.elasticsearch.indexing.IspitIndexer;
import com.ff.filip.elasticsearch.indexing.StudentIndexer;
import java.util.List;

/**
 *
 * @author filip
 */
public class FacadeClass {

    private static FacadeClass facadeClass;
    private ElasticsearchAdministration ea;
    private StudentIndexer si;
    private IspitIndexer ii;

    public static FacadeClass getInstance() {
        if (facadeClass == null) {
            facadeClass = new FacadeClass();
        }
        return facadeClass;
    }

    public boolean indexStudents(List<Student> listStudent) {
        boolean flag = true;
        si = new StudentIndexer();
        si.indexStudents(listStudent);

        return flag;
    }

    public boolean indexIspit(List<Ispit> listIspit) {
        boolean flag = true;
        ii = new IspitIndexer();
        ii.indexStudents(listIspit);

        return flag;
    }

    public boolean createIndexes() {
        ea = new ElasticsearchAdministration();
        ea.createAllIndexes();
        return true;
    }
}
