/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Job;
import br.com.bob.dashboard.model.repository.JobDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class JobService {
    
    @Inject private JobDAO dao;
    @Inject private EntityManager manager;
    
    @Transactional
    public void create(final Job job) {
        final Job found = dao.findByTitleEqual(job.getTitle());
        if (found == null)
            dao.save(job);
        else
            throw new BusinessException("Cargo: " + job.getTitle() + " já cadastrado");
    }
    
    @Transactional
    public void remove(Job job) {
        if (dao.getEmployees(job).isEmpty()) {
            try {
                job = manager.getReference(Job.class, job.getId());
                dao.remove(job);
            } catch (Exception e) {
                throw new BusinessException("Erro ao remover o cargo", e);
            }
        } else
            throw new BusinessException("O cargo contém colaboradores associados. "
                    + "Nao é possível remover");
    }
    
    public List<Job> getAll() {
        return dao.findAll();
    }
    
}
