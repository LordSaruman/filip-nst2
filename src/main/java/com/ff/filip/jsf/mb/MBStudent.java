/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Student;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import com.ff.filip.jpa.dbb.IspitService;
import com.ff.filip.jpa.dbb.IspitnirokService;
import com.ff.filip.jpa.dbb.StudentService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 *
 * @author filip
 */
@Named(value = "mbStudent")
@SessionScoped
public class MBStudent implements Serializable {

    @Inject
    private StudentService ss;
    
    private Student student;
    private Student studentForEditing;
    private Student studentForDeleting;
    private List<Student> list;
    private List<Mesto> listMesto;
    private boolean isInitialized = false;
    private String wildCardQuery;

    @PostConstruct
    public void init() {
        student = new Student();
        studentForDeleting = new Student();
        studentForEditing = new Student();
        wildCardQuery = "";
        findAllStudent();
        findAllMesto();
    }

    public MBStudent() {
        student = new Student();
        studentForDeleting = new Student();
        studentForEditing = new Student();
        wildCardQuery = "";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudentForEditing() {
        return studentForEditing;
    }

    public void setStudentForEditing(Student studentForEditing) {
        this.studentForEditing = studentForEditing;
    }

    public Student getStudentForDeleting() {
        return studentForDeleting;
    }

    public void setStudentForDeleting(Student studentForDeleting) {
        this.studentForDeleting = studentForDeleting;
        isInitialized = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje! ", "  Student je postavljen za brisanje."));
    }

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public List<Mesto> getListMesto() {
        return listMesto;
    }

    public void setListMesto(List<Mesto> listMesto) {
        this.listMesto = listMesto;
    }

    public String getWildCardQuery() {
        return wildCardQuery;
    }

    public void setWildCardQuery(String wildCardQuery) {
        this.wildCardQuery = wildCardQuery;
    }

    public void findAllStudent() {
        list = ss.findAllStudent();
    }

    public void findAllMesto() {
        listMesto = ss.findAllMesto();
    }

    public void deleteStudent() {
        if (isInitialized) {
            ss.deleteStudentById(studentForDeleting);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno obrisan."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje!: ", "  Niste postavili studenta za brisanje"));
        }
    }

    public void persistStudent() {
        boolean flag = checkBrInd(student);

        if (flag == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje: ", "  Student nije sacuvan!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska: ", "  Broj indeksa vec postoji u bazi. Molimo izaberite drugi broj indeksa."));
        } else {
            ss.persistStudent(student);
            student = new Student();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno sacuvan."));
        }
    }

    public void persistEditStudent() {
        ss.persistEdit(studentForEditing);
        studentForEditing = new Student();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Student je uspesno izmenjen i sacuvan."));
    }

    public void loadMesta() {
        findAllMesto();
    }

    private boolean checkBrInd(Student student) {
        boolean flag = false; //br indeksa ne postoji u bazi
        flag = ss.checkBrInd(student);

        return flag;
    }

    public void fullTextSearch() {
        list.clear();
        List<Student> listTemp = new ArrayList<>();
        Student temporary = null;

        try {
            QueryBuilder qb = QueryBuilders.queryStringQuery(wildCardQuery + "*")
                    .defaultField("BrInd")
                    .defaultField("Ime")
                    .defaultField("Prezime")
                    .defaultOperator(Operator.AND);

            SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                    .prepareSearch(ESIndex.STUDENT.name().toLowerCase())
                    .setTypes(ESIndex.STUDENT.getTypes()[0])
                    .setQuery(qb)
                    .execute().actionGet();

            if (searchResponse != null) {
                for (SearchHit hit : searchResponse.getHits()) {
                    try {
                        Map<String, Object> map = hit.getSourceAsMap();
                        
                        String brIndeksa = map.get("BrInd").toString();
                        String ime = map.get("Ime").toString();
                        String prezime = map.get("Prezime").toString();
                        Mesto mesto = new Mesto();
                        mesto = (Mesto) map.get("mesto");
                        
                        temporary = new Student(brIndeksa, ime, prezime,mesto);
                        
                    } catch (Exception e) {
                        System.out.println("Exception in fullTextSearch: " + e.getMessage());
                    }
                    list.add(temporary);
                    listTemp.add(temporary);
                }
            }
            setList(listTemp);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
        }

    }
}
