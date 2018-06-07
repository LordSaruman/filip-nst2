/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.elasticsearch.administration.ESIndex;
import com.ff.filip.elasticsearch.administration.ElasticClient;
import com.ff.filip.jpa.dbb.IspitService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 *
 * @author filip
 */
@Named(value = "mbIspit")
@SessionScoped
public class MBIspit implements Serializable {

    @Inject
    private IspitService ispitService;

    private Ispit ispit;
    private Ispit ispitForEditing;
    private Ispit ispitForDeleting;
    private List<Ispit> listIspit;
    private boolean isInitialized = false;
    private String wildCardQuery;

    @PostConstruct
    public void init(){
        ispit = new Ispit();
        ispitForEditing = new Ispit();
        ispitForDeleting = new Ispit();
        wildCardQuery = "";
        findAllIspit();
    }
    
    public MBIspit() {
        ispit = new Ispit();
        ispitForEditing = new Ispit();
        ispitForDeleting = new Ispit();
        wildCardQuery = "";
    }

    public Ispit getIspit() {
        return ispit;
    }

    public void setIspit(Ispit ispit) {
        this.ispit = ispit;
    }

    public Ispit getIspitForEditing() {
        return ispitForEditing;
    }

    public void setIspitForEditing(Ispit ispitForEditing) {
        this.ispitForEditing = ispitForEditing;
    }

    public Ispit getIspitForDeleting() {
        return ispitForDeleting;
    }

    public void setIspitForDeleting(Ispit ispitForDeleting) {
        this.ispitForDeleting = ispitForDeleting;
        isInitialized = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje! ", "  Ispit je postavljen za brisanje."));
    }

    public List<Ispit> getListIspit() {
        return listIspit;
    }

    public void setListIspit(List<Ispit> listIspit) {
        this.listIspit = listIspit;
    }

    public String getWildCardQuery() {
        return wildCardQuery;
    }

    public void setWildCardQuery(String wildCardQuery) {
        this.wildCardQuery = wildCardQuery;
    }

    public void findAllIspit() {
        listIspit = ispitService.findAllIspit();
    }

    public void deleteIspit() {
        if (isInitialized) {
            ispitService.deleteIspitById(ispitForDeleting);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno obrisan."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje!: ", "  Niste postavili ispit za brisanje"));
        }
    }

    public void persistIspit() {
        boolean flag = checkIspitPass(ispit);

        if (flag == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Upozorenje: ", "  Ispit nije sacuvan!"));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greska: ", "  Sifra ispita vec postoji u bazi. Molimo izaberite drugu sifru."));
        } else {
            ispitService.persistIspit(ispit);
            ispit = new Ispit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno sacuvan."));
        }
    }

    public void persisEditStudent() {
        ispitService.persistEdit(ispitForEditing);
        ispitForEditing = new Ispit();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO: ", "  Ispit je uspesno izmenjen i sacuvan."));
    }

    private boolean checkIspitPass(Ispit ispit) {
        boolean flag = false; //ne postoji sifra u bazi
        flag = ispitService.checkIspitPass(ispit);

        return flag;
    }

    public void printPDFIspit() throws JRException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        Date date = new Date();
        System.out.println("usao2");
        List<Ispit> forPrinting = new ArrayList<>();
        findAllIspit();
        forPrinting = this.listIspit;
        for (Ispit i : forPrinting) {
            System.out.println(i.getSifraIspita());
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(forPrinting);

        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/IspitMaster.jasper");
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application / pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=StudentMaster_" + date + ".pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public void fullTextSearch() {
        listIspit.clear();
        List<Ispit> listTemp = new ArrayList<>();
        Ispit temporary = null;

        try {
            QueryBuilder qb = QueryBuilders.queryStringQuery(wildCardQuery + "*")
                    .defaultField("NazivIspita")
                    .defaultOperator(Operator.AND);

            SearchResponse searchResponse = ElasticClient.getInstance().getClient()
                    .prepareSearch(ESIndex.ISPIT.name().toLowerCase())
                    .setTypes(ESIndex.ISPIT.getTypes()[0])
                    .setQuery(qb)
                    .execute().actionGet();

            if (searchResponse != null) {
                for (SearchHit hit : searchResponse.getHits()) {
                    try {
                        Map<String, Object> map = hit.getSourceAsMap();
                        int sifraIspita = Integer.parseInt(map.get("SifraIspita").toString());
                        String nazivIspita = map.get("NazivIspita").toString();

                        temporary = new Ispit(sifraIspita, nazivIspita);

                    } catch (Exception e) {
                        System.out.println("Exception in fullTextSearch: " + e.getMessage());
                    }
                    listIspit.add(temporary);
                    listTemp.add(temporary);
                }
            }
            setListIspit(listTemp);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.toString());
        }
    }

}
