/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Metric;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.model.repository.MetricDAO;
import br.com.bob.dashboard.web.qualifiers.LoggedIn;
import java.util.Date;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 * @param <E> A entidade que será tratada
 */
public class MetricService<E extends Metric> {
    @Inject private Event<Metric> history;
    @Inject @LoggedIn private User user;
    @Inject private MetricDAO<E> dao;
    
    @Transactional
    public void save(final E metric) {
        try {
            final E found = findByTeam(metric.getClass(), metric.getTeam());
            
            if (found != null) {
                metric.setId(found.getId());
                history.fire(found);
            }
            
            metric.setUser(user);
            metric.setDate(new Date());
            dao.save(metric);
        } catch (Exception e) {
            throw new BusinessException("Erro ao salvar indicador", e);
        }
    }
    
    public E findByTeam(final Class<? extends Metric> entity, final Team team) {
        return dao.findByTeamEqual(entity, team);
    }
}