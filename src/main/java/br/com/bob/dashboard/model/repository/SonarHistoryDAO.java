/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.history.SonarHistory;
import java.util.Date;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface SonarHistoryDAO extends EntityRepository<SonarHistory, Long> {
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    SonarHistory findByTeamEqualAndDateEqual(Team team, Date date);
}