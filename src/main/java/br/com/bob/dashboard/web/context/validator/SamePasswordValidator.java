/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.ValidationException;

/**
 *
 * @author Vitor
 */
@Named("samePwdValidator") 
@RequestScoped
public class SamePasswordValidator {
    
    private String source;
    private String target;
    
    public void validateTarget(FacesContext ctx, UIComponent comp, Object value) {
        target = value.toString();
        validate();
    }
    
    public void validateSource(FacesContext ctx, UIComponent comp, Object value) {
        source = value.toString();
        validate();
    }
    
    private void validate() {
        if (source != null && target != null) {
            if (!target.equals(source)) {
                throw new ValidationException("As senhas devem ser iguais");
            }    
        }
    }
}