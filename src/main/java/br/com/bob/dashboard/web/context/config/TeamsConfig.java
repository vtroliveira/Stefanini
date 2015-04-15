/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.config;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.enums.BooleanChar;
import br.com.bob.dashboard.service.TeamService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@Startup @Singleton
public class TeamsConfig {
    @Inject private TeamService service;
    
    @PostConstruct
    public void init() {
        create("Arv", BooleanChar.S);
        create("Boarding", BooleanChar.S);
        create("Exception Management", BooleanChar.S);
        create("Hierarchy", BooleanChar.S);
        create("Reports", BooleanChar.S);
        create("Settlement", BooleanChar.S);
        create("Cross", BooleanChar.N);
        create("Gerência", BooleanChar.N);
    }
    
    private void create(final String name, final BooleanChar hasMetrics) {
        final Team team = new Team();
        team.setName(name);
        team.setMetrics(hasMetrics);
        try {
            service.create(team);
        } catch (BusinessException e) {
            System.out.println(e.getMessage());
        }
    }
}