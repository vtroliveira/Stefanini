/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.enums.BooleanChar;
import br.com.bob.dashboard.model.repository.TeamDAO;
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
public class TeamService {
    
    @Inject private TeamDAO dao;
    @Inject private EntityManager manager;
    
    @Transactional
    public void create(final Team team) {
        final Team found = find(team.getName());
        if (found == null)
            dao.save(team);
        else
            throw new BusinessException("Time: " + team.getName() + " já cadastrado");
    }
    
    @Transactional
    public void remove(Team team) {
        if (hasEmployees(team)) 
            throw new BusinessException("O time contém colaboradores associados. "
                        + "Não é possível remover");
        
        if (hasMetrics(team))
            throw new BusinessException("O time contém métricas associadas. "
                    + "Não é possível remover");
        
        try {
            team = manager.getReference(Team.class, team.getId());
            dao.remove(team);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover o time", e);
        }
    }
    
    public boolean hasEmployees(final Team team) {
        return !dao.getEmployees(team).isEmpty();
    }
    
    public boolean hasMetrics(final Team team) {
        return !dao.getMetrics(team).isEmpty();
    }
    
    public List<Team> getAll() {
        return dao.findAllOrderByName();
    }
    
    public List<Team> getAllWithMetrics() {
        return dao.findByMetricsOrderByName(BooleanChar.S);
    }
    
    public Team find(final String name) {
        return dao.findByNameEqual(name);
    }
}
