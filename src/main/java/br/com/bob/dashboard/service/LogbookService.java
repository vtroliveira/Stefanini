/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Logbook;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.repository.LogbookDAO;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RequestScoped
public class LogbookService {
    @Inject private EntityManager manager;
    @Inject private LogbookDAO dao;
    
    @Transactional
    public void save(final Logbook log) {
        try {
            log.setInsertIn(new Date());
            dao.save(log);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir diário de bordo", e);
        }
    }
    
    @Transactional
    public void remove(final Logbook log) {
        try {
            final Logbook ref = manager.getReference(Logbook.class, log.getId());
            dao.remove(ref);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover diário de bordo", e);
        }
    }
    
    public List<Logbook> getLogbooks(final Team team) {
        return dao.findByTeamEqual(team).getResultList();
    }
    
    public List<Logbook> getLogbooks(final Team team, int first, int max) {
        return dao.findByTeamEqual(team)
                  .firstResult(first)
                  .withPageSize(max)
                  .getResultList();
    }
}
