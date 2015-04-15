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
import br.com.bob.dashboard.model.history.MetricHistory;
import br.com.bob.dashboard.model.repository.MetricHistoryDAO;
import br.com.bob.dashboard.web.beans.util.MetricTableHistory;
import br.com.bob.dashboard.web.qualifiers.LoggedIn;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Asynchronous;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
public class HistoryService implements Serializable {
    private final String parentFieldName = "parentId";
    @Inject private MetricHistoryDAO dao;
    @Inject @LoggedIn private User logged;
    
    @Asynchronous
    public void history(@Observes Metric metric) {
        final MetricHistory history = build(metric);
        save(history);
    }
    
    public <T> T getHistory(final Class<T> type, final Team team, final Date date) {
        return dao.getHistoryByTeamUntil(type, team, date);
    }
    
    public <T> List<T> getHistory(final Class<T> type) {
        return dao.getAllHistory(type);
    }
    
    private MetricHistory build(final Metric metric) {
        MetricHistory history;
        try {
            history = metric.historyClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BusinessException("Erro ao criar histórico", e);
        }
        
        setEqualFields(metric, history);        
        return history;
    }
    
    private void setEqualFields(final Metric metric, final MetricHistory history) {
        final List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(metric.getClass().getDeclaredFields()));
        fields.addAll(Arrays.asList(metric.getClass().getSuperclass().getDeclaredFields()));
        
        for (final Field field : fields) {
            if (field.isAnnotationPresent(Column.class) ||
                field.isAnnotationPresent(JoinColumn.class)) {
                
                Class type = history.getClass();
                Field histField = null;
                
                try {
                    field.setAccessible(true);
                    histField = getField(type, field.isAnnotationPresent(Id.class) ? parentFieldName : field.getName());
                } catch (NoSuchFieldException e) {
                    try {
                        type = type.getSuperclass();
                        histField = getField(type, field.isAnnotationPresent(Id.class) ? parentFieldName : field.getName());
                    } catch (NoSuchFieldException ex) {
                        System.out.println("Atributo não encontrado na entidade " +  type.getSimpleName() + ": " + field.getName());
                    }
                }
                
                if (histField != null) {
                    try {
                        histField.setAccessible(true);
                        histField.set(history, field.get(metric));
                    } catch (IllegalAccessException e) {
                        throw new BusinessException("Erro ao mapear campos alterados no histórico", e);
                    }
                }
            }
        }
    }
    
    private Field getField(final Class type, final String fieldName) throws NoSuchFieldException {
        return type.getDeclaredField(fieldName);
    }
    
    private void save(final MetricHistory history) {
        try {
            history.setDate(new Date());
            final MetricHistory found = dao.getHistoryByTeamUntil(history.getClass(), 
                    history.getTeam(), history.getDate());
            
            if (found != null)
                history.setId(found.getId());
            
            history.setUser(logged);
            dao.save(history);
        } catch (Exception e) {
            throw new BusinessException("Erro ao persistir histórico "
                    + "de alterações", e);
        }
    }
}