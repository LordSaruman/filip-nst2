/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jasper.report;

import com.ff.filip.domen.Mesto;
import com.ff.filip.domen.Polaganje;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintLine;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.base.JRBasePrintLine;
import net.sf.jasperreports.engine.base.JRBasePrintPage;
import net.sf.jasperreports.engine.base.JRBasePrintText;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 *
 * @author filip
 */
public class Jasper {

    public void fill(List<Mesto> lm) throws JRException {
        JasperPrint jprint = getJasperPrint(lm);
        JRSaver.saveObject(jprint, "izvestaj.jprint");
    }

    public void fillPol(List<Polaganje> po) throws JRException {
        JasperPrint jprint = getJasperPrintPo(po);
        JRSaver.saveObject(jprint, "izvestaj.jprint");
    }

    private JasperPrint getJasperPrint(List<Mesto> lm) {
        JasperPrint jasperPrint = new JasperPrint();
        jasperPrint.setName("Izvestaj");
        jasperPrint.setPageHeight(800);
        jasperPrint.setPageWidth(600);

        JRPrintPage page = new JRBasePrintPage();
        JRPrintLine line = new JRBasePrintLine(jasperPrint.getDefaultStyleProvider());
        line.setX(40);
        line.setY(50);
        line.setWidth(500);
        line.setHeight(5);
        page.addElement(line);

        int y = 150;
        for (Mesto m : lm) {
            JRPrintText text = new JRBasePrintText(jasperPrint.getDefaultStyleProvider());
            text.setX(40);
            text.setY(y);
            text.setWidth(500);
            text.setHeight(50);
            text.setTextHeight(50);
            text.setText(m.getPtt() + "\t\t\t" + m.getNaziv());
            page.addElement(text);
            y = y + 50;
        }
        jasperPrint.addPage(page);
        return jasperPrint;
    }

    private JasperPrint getJasperPrintPo(List<Polaganje> po) {
        JasperPrint jasperPrint = new JasperPrint();
        jasperPrint.setName("Izvestaj");
        jasperPrint.setPageHeight(800);
        jasperPrint.setPageWidth(600);

        JRPrintPage page = new JRBasePrintPage();
        JRPrintLine line = new JRBasePrintLine(jasperPrint.getDefaultStyleProvider());
        line.setX(40);
        line.setY(50);
        line.setWidth(500);
        line.setHeight(5);
        page.addElement(line);

        int y = 150;
        for (Polaganje polaganje : po) {
            JRPrintText text = new JRBasePrintText(jasperPrint.getDefaultStyleProvider());
            text.setX(40);
            text.setY(y);
            text.setWidth(500);
            text.setHeight(50);
            text.setTextHeight(50);
            text.setText(polaganje.getIdPolaganje()
                    + "\t" + polaganje.getIspit().getNazivIspita()
                    + "\t" + polaganje.getStudent().getIme() + " " + polaganje.getStudent().getPrezime()
                    + "\t" + polaganje.getOcena());
            page.addElement(text);
            y = y + 50;
        }
        jasperPrint.addPage(page);
        return jasperPrint;
    }
}
