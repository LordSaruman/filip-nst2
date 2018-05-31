/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ff.filip.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author filip
 */
@FacesValidator(value = "ocenaValidator")
public class OcenaValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        System.out.println("USAO U VALIDATOR");
        
        if (value != null) {
            String ocena = value.toString();
            for (int i = 0; i < ocena.length(); i++) {
                if (!Character.isDigit(ocena.charAt(i))) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocena mora sadrzati samo cifre", "PORUKA "));
                }
            }
            
            int mark = Integer.parseInt(ocena);
            if (mark > 10 || mark < 5) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ocena moze biti u intervalu [5-10]", "PORUKA "));
            }
        }
    }
    
}
