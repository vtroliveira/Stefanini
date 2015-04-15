/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.enums.BooleanChar;
import br.com.bob.dashboard.service.TeamService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import br.com.bob.dashboard.web.qualifiers.AllTeams;
import br.com.bob.dashboard.web.qualifiers.Teams;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class CadTeamBean extends ManagedBean {
    @Getter @Inject @Teams private List<Team> teams;
    @Getter @Inject @AllTeams private List<Team> allTeams;
    @Getter @Setter private Team selected;
    @Inject private TeamService service;
    
    @PostConstruct
    public void init() {
        selected = new Team();
    }
    
    public String save() {
        service.create(selected);
        addInList();
        info("Time cadastrado com sucesso");
        return redirect("team_config");
    }
    
    private void addInList() {
        if (BooleanChar.S.equals(selected.getMetrics()))
            teams.add(selected);
        
        allTeams.add(selected);
    }
    
    public List<BooleanChar> getCharOptions() {
        return Arrays.asList(BooleanChar.values());
    }
}