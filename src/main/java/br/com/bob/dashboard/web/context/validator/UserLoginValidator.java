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
@Named("userLoginValidator")
@RequestScoped
public class UserLoginValidator {
    
    public void validate(FacesContext ctx, UIComponent comp, Object value) {
        if (value.toString().toLowerCase().contains("admin"))
            throw new ValidationException("O login não deve conter a palavra admin");
    }
}