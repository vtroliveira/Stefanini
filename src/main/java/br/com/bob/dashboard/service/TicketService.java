/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.Ticket;
import br.com.bob.dashboard.model.TimeboxDef;
import br.com.bob.dashboard.model.enums.Phase;
import br.com.bob.dashboard.model.repository.TicketDAO;
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
public class TicketService {
    @Inject private TicketDAO dao;
    @Inject private TimeboxService tbService;
    @Inject private EntityManager em;
    
    public long countTotalTickets(final Team team) {
        return dao.findByTeamEqualAndPhaseNotEqual(team, Phase.Finished)
                  .count();
    }
    
    public long inTest(final Team team) {
        return dao.findByTeamEqualAndPhaseEqual(team, Phase.Test)
                  .count();
    }
    
    public long inCoding(final Team team) {
        return dao.findByTeamEqualAndPhaseEqual(team, Phase.Coding)
                  .count();
    }
    
    public long inSolution(final Team team) {
        return dao.findByTeamEqualAndPhaseEqual(team, Phase.Solution)
                  .count();
    }
    
    @Transactional
    public void create(final Ticket ticket, final TimeboxDef tb) {
        try {
            final TimeboxDef def = tbService.create(tb);
            ticket.addTimebox(def); 
            ticket.setPhase(def.getPhase());
            dao.save(ticket);
        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar produto. "
                    + "Favor contatar o administrador", e);
        }
    }
    
    public boolean exists(final String product, final int timebox, final Phase phase, final Team team) {
        return dao.getTicket(product, team, phase, timebox) != null;
    }
    
    public List<Ticket> getTickets() {
        return dao.findAll();
    }
    
    public List<Ticket> getTickets(final int first, final int max) {
        return dao.findAll(first, max);
    }
    
    public List<Ticket> getTickets(final Team team, final Phase phase, final int first, final int max) {
        return dao.findByTeamEqualAndPhaseEqual(team, phase)
                  .firstResult(first)
                  .withPageSize(max)
                  .getResultList();
    }
    
    public List<Ticket> getTicketsByTeamOrPhase(final Team team, final Phase phase, final int first, final int max) {
        return dao.findByTeamEqualOrPhaseEqual(team, phase)
                  .firstResult(first)
                  .withPageSize(max)
                  .getResultList();
    }
    
    public void remove(final Ticket ticket) {
        try {
            final Ticket ref = em.getReference(Ticket.class, ticket.getId());
            dao.remove(ref);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover produto. "
                    + "Favor contatar o administrador", e);
        }
    }
}