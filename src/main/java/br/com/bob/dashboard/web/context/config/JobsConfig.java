/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.config;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Job;
import br.com.bob.dashboard.service.JobService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@Startup @Singleton
public class JobsConfig {
    @Inject private JobService service;
    
    @PostConstruct
    public void init() {
        create("Arquiteto");
        create("Apoio a Gestão");
        create("Gerente");
        create("Java Pleno");
        create("Java Sênior");
    }
    
    private void create(final String name) {
        final Job job = new Job();
        job.setTitle(name);
        
        try {
            service.create(job);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
}