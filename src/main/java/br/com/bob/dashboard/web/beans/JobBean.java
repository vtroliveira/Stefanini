/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Job;
import br.com.bob.dashboard.service.JobService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.util.List;
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
public class JobBean extends ManagedBean {
    @Getter @Setter private Job selected;
    @Inject private JobService service;
    
    public void remove() {
        service.remove(selected);
        info("Cargo removido com sucesso");
    }
    
    public List<Job> getJobs() {
        return service.getAll();
    }
}