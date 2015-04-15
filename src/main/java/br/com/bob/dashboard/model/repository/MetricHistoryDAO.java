/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.history.MetricHistory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Vitor
 */
public class MetricHistoryDAO {
    
    @Inject private EntityManager manager;
    
    public MetricHistory save(final MetricHistory history) {
        return manager.merge(history);
    }
    
    public void remove(final MetricHistory history) {
        final MetricHistory ref = manager.getReference(history.getClass(), history.getId());
        manager.remove(ref);
    }
    
    public <T> List<T> getAllHistory(final Class<T> type) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select e from ")
          .append(type.getSimpleName())
          .append(" e");
        
        try {
            final Query query = manager.createQuery(sb.toString());
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return new ArrayList<>();
        }
    }
    
    public <T> T getHistoryByTeamUntil(final Class<T> type, final Team team, final Date until) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select e from ")
          .append(type.getSimpleName())
          .append(" e where e.team = :team")
          .append(" and e.date = :date");
        
        try {
            final Query query = manager.createQuery(sb.toString());
            query.setParameter("team", team);
            query.setParameter("date", until, TemporalType.DATE);
            return (T) query.getSingleResult();
        } catch (NoResultException e) {}
        
        return null;
    }
    
    public <T> T getMetricHistory(final Class<T> type, final Team team) {
        
        final StringBuilder sb = new StringBuilder();
        sb.append("select e from ")
          .append(type.getSimpleName())
          .append(" e where e.team = :team");
        
        try {
            final Query query = manager.createQuery(sb.toString());
            query.setParameter("team", team);
            return (T) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }
}