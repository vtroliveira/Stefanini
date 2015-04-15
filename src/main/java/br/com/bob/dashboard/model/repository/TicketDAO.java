/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.Ticket;
import br.com.bob.dashboard.model.enums.Phase;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface TicketDAO extends EntityRepository<Ticket, Long> {

    @Query(singleResult = SingleResultType.OPTIONAL)
    Ticket findByProductEqual(String product);
    
    @Query(value = "select e from Ticket e join e.timeboxes tb "
            + "where e.product = ?1 "
            + "and e.team = ?2 "
            + "and e.phase = ?3 "
            + "and tb.number = ?4", singleResult = SingleResultType.OPTIONAL)
    Ticket getTicket(String product, Team team, Phase phase, int tbNumber);
    
    @Query("select e from Ticket e where e.team = ?1 and e.phase != ?2")
    QueryResult<Ticket> findByTeamEqualAndPhaseNotEqual(Team team, Phase phase);
    QueryResult<Ticket> findByTeamEqualOrPhaseEqual(Team team, Phase phase);
    QueryResult<Ticket> findByTeamEqualAndPhaseEqual(Team team, Phase phase);
}