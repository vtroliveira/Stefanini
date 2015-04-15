/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @RequestScoped
public class TemplateBean extends ManagedBean {
    
    public void clearMessages() {
        getFlash().clear();
    }
}
