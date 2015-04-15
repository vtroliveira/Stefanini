/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Metric;
import br.com.bob.dashboard.model.Team;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Vitor
 * @param <E> A entidade
 */
public class MetricDAO<E extends Metric> {
    
    @Inject private EntityManager manager;

    public E findByTeamEqual(final Class<? extends Metric> entity, final Team team) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select e from ")
          .append(entity.getSimpleName())
          .append(" e where e.team = ?1");
        
        try {
            return (E) manager.createQuery(sb.toString())
                              .setParameter(1, team)
                              .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public void save(final E metric) {
        manager.merge(metric);
    }
    
    public void remove(final E metric) {
        final Metric ref = manager.getReference(metric.getClass(), metric.getId());
        manager.remove(ref);
    }
}