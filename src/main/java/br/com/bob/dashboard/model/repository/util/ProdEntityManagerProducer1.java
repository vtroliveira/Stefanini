/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository.util;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.deltaspike.core.api.exclude.Exclude;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

/**
 *
 * @author Vitor
 */
@Exclude(ifProjectStage = ProjectStage.Development.class)
public class ProdEntityManagerProducer1 implements Serializable {
    
    @Produces @ApplicationScoped
    public EntityManagerFactory createFactory() {
        return Persistence.createEntityManagerFactory("bob-dashboard-prod");
    }
    
    @Produces @RequestScoped
    public EntityManager create(EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
    
    public void close(@Disposes EntityManager manager) {
        if (manager.isOpen())
            manager.close();
    }
}