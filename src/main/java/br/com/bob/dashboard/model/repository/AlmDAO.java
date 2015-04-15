/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.ALM;
import br.com.bob.dashboard.model.Team;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryResult;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface AlmDAO extends EntityRepository<ALM, Long> {
    
    @Query("select e from ALM e where e.ticket.team = ?1")
    QueryResult findByTeam(final Team team);
}