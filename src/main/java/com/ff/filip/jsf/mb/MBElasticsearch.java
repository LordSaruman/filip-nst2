/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import com.ff.filip.elasticsearch.administration.ElasticsearchAdministration;
import com.ff.filip.elasticsearch.administration.FacadeClass;
import com.ff.filip.elasticsearch.indexing.PolaganjeIndexer;
import com.ff.filip.elasticsearch.indexing.StudentIndexer;
import com.ff.filip.jpa.controller.Controller;
import com.ff.filip.jpa.dbb.ElasticsearchSearchService;
import com.ff.filip.jpa.dbb.IspitService;
import com.ff.filip.jpa.dbb.PolaganjeService;
import com.ff.filip.jpa.dbb.StudentService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;

/**
 *
 * @author filip
 */
@Named(value = "mbElasticsearch")
@SessionScoped
public class MBElasticsearch implements Serializable {

    @Inject
    private ElasticsearchSearchService ess;

    @Inject
    private StudentService ss;

    @Inject
    private IspitService is;
    
    @Inject
    private PolaganjeService ps;

    private Student selectedStudent;
    private Student student;
    private List<Student> studentList;
    private List<Mesto> listMesto;
    private String wildCardQuery;
    private boolean indexesCreated;

    public MBElasticsearch() {
        student = new Student();
        selectedStudent = new Student();
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getWildCardQuery() {
        return wildCardQuery;
    }

    public void setWildCardQuery(String wildCardQuery) {
        this.wildCardQuery = wildCardQuery;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Mesto> getListMesto() {
        return listMesto;
    }

    public void setListMesto(List<Mesto> listMesto) {
        this.listMesto = listMesto;
    }

    public void findAllMesto() {
        listMesto = ss.findAllMesto();
    }

    public void loadMesta() {
        findAllMesto();
    }

    public void indexStudent() {
        boolean flag = true;
        List<Student> listStudent = ss.findAllStudent();
        
        if(!indexesCreated){
            createIndexes();
        }
        
        flag = FacadeClass.getInstance().indexStudents(listStudent);
        if (flag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Uspesno je izvrseno indeksiranje studenata."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska! ", "  Doslo je do greske prilikom indeksiranja studenata."));
        }
    }

    public void indexIspit() {
        boolean flag = true;
        List<Ispit> listIspit = is.findAllIspit();
        
        if(!indexesCreated){
            createIndexes();
        }
        
        flag = FacadeClass.getInstance().indexIspit(listIspit);
        if (flag) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Uspesno je izvrseno indeksiranje ispita."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Greska! ", "  Doslo je do greske prilikom indeksiranja ispita."));
        }
    }

    public void createIndexes() {
        boolean flag = true;
        flag = FacadeClass.getInstance().createIndexes();
        indexesCreated = flag;
    }

    public void clearWildCardQuery() {
        wildCardQuery = "";
    }

    public void loadAll() {
    }

    public void findAllStudent() {
        SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                .prepareSearch(ESIndex.STUDENT.name().toLowerCase())
                .setTypes(ESIndex.STUDENT.getTypes()[0])
                .execute().actionGet();

        Student temporary = new Student();

        if (searchResponse != null) {
            for (SearchHit hit : searchResponse.getHits()) {
//                Mesto mesto = new Mesto();
//                int ptt = Integer.parseInt(hit.getId());
//                mesto = Controller.getInstance().findMestoByPtt(ptt);

                Map<String, Object> map = hit.getSourceAsMap();

                String brIndeksa = map.get("BrInd").toString();
                String ime = map.get("Ime").toString();
                String prezime = map.get("Prezime").toString();

                temporary = new Student(brIndeksa,
                        ime,
                        prezime,
                        null);
                studentList.add(temporary);
            }
        }
    }

    public void fullTextSearch() {
        
    }

    public void prepareDocumentList() {

    }

    public void persistStudent() {

    }

    public void deleteStudent() {

    }

}
