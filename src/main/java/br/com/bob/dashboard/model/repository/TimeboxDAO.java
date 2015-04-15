/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.TimeboxDef;
import br.com.bob.dashboard.model.enums.Phase;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface TimeboxDAO extends EntityRepository<TimeboxDef, Long> {

    @Query(value = "select e from TimeboxDef e where e.number = ?1 and e.phase = ?2", singleResult = SingleResultType.OPTIONAL)
    TimeboxDef find(int number, Phase phase);
}