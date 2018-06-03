/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.mb;

import com.ff.filip.domen.Ispit;
import com.ff.filip.domen.IspitniRok;
import com.ff.filip.domen.Polaganje;
import com.ff.filip.domen.Student;
import com.ff.filip.jpa.dbb.PolaganjeService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
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

/**
 *
 * @author filip
 */
@Named(value = "mbJasper")
@SessionScoped
public class MBJasperReport implements Serializable {

    @Inject
    private PolaganjeService polaganjeService;

    private List<Polaganje> listPolaganje;
    private List<Polaganje> listPolaganjeDuplikat;
    private Date date;

    public MBJasperReport() {
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Polaganje> getListPolaganjeDuplikat() {
        return listPolaganjeDuplikat;
    }

    public void setListPolaganjeDuplikat(List<Polaganje> listPolaganjeDuplikat) {
        this.listPolaganjeDuplikat = listPolaganjeDuplikat;
    }

    public List<Polaganje> getListPolaganje() {
        return listPolaganje;
    }

    public void setListPolaganje(List<Polaganje> listPolaganje) {
        this.listPolaganje = listPolaganje;
    }


    public void printPDFStudent(Student student) throws JRException, IOException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        System.out.println("usao1");
        listPolaganje = polaganjeService.findAllPolaganje(); //sva polaganja
        List<Polaganje> withSpecificStudent = new ArrayList<>(); //polaganja za odredjenog studenta
        for (Polaganje polaganje : listPolaganje) {
            if (polaganje.getStudent().getBrInd().equals(student.getBrInd())) {
                withSpecificStudent.add(polaganje);
                System.out.println(polaganje.getIdPolaganje());
            }
        }
        
        //Kada se prosledjuje resurs (parametar neki, u ovom slucaju lista), main report NE PROSLEDJUJE prvi element subreportu
        //kao workaround koji jedini radi kod mene jeste da ubacim random prvi element (ali za istog studenta za koji se radi izvestaj)
        Ispit i = new Ispit(1, "nazivIspita");
        IspitniRok ir = new IspitniRok(2, "nazivIspitnogRoka");
        Polaganje p = new Polaganje(1, student, ir, i, 1, date);
        withSpecificStudent.add(0, p);

        listPolaganjeDuplikat = withSpecificStudent;
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(withSpecificStudent);

        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/StudentMaster.jasper");
        parameters.put("listPolaganjeDuplikat", beanCollectionDataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);
        
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application / pdf");
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=StudentMaster_" + date + ".pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }
}
