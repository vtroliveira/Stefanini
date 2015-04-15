/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Job;
import br.com.bob.dashboard.service.JobService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class CadJobBean extends ManagedBean {
    @Getter @Setter private Job selected;
    @Inject private JobService service;
    
    @PostConstruct
    public void init() {
        selected = new Job();
    }
    
    public String save() {
        service.create(selected);
        info("Cargo cadastrado com sucesso");
        return redirect("job_config");
    }
}