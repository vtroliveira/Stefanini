/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans.util;

import br.com.bob.dashboard.model.Metric;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.MetricService;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 * @param <E> A entidade tratada pelo bean
 */
public abstract class MetricBean<E extends Metric> extends ManagedBean {
    private final String teamParam = "team";
    @Getter @Setter protected E metric;
    @Inject protected MetricService<E> service;
    
    @PostConstruct
    public void init() {
        try {
            metric = getType().newInstance();
            metric.setTeam(getParam(teamParam, Team.class));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace(System.err);
        }
    }
    
    public E getInfoByTeam(Team team) {
        return service.findByTeam(getType(), team);
    }
    
    private Class<E> getType() {
        final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        final Type[] args = type.getActualTypeArguments();
        return (Class<E>) args[0];
    }
    
    public String save() {
        doSave();
        return "index?faces-redirect=true";
    }
    
    public String saveAndNext(final String next) {
        doSave();
        putParam(teamParam, metric.getTeam());
        return next.concat("?faces-redirect=true");
    }
    
    private void doSave() {
        service.save(metric);
    }
}
