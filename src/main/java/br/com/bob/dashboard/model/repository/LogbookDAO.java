/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Logbook;
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
public interface LogbookDAO extends EntityRepository<Logbook, Long> {

    @Query(value = "select e from Logbook e where e.team = ?1 order by e.id desc")
    QueryResult<Logbook> findByTeamEqual(final Team team);
}