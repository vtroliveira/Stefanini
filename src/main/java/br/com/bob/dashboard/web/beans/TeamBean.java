/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.TeamService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import br.com.bob.dashboard.web.qualifiers.AllTeams;
import br.com.bob.dashboard.web.qualifiers.Teams;
import java.util.List;
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
public class TeamBean extends ManagedBean {
    @Getter @Setter private Team selected;
    @Getter @Inject @Teams private List<Team> teams;
    @Getter @Inject @AllTeams private List<Team> allTeams;
    @Inject private TeamService service;
    
    public void remove() {
        service.remove(selected);
        allTeams.remove(selected);
        teams.remove(selected);
        info("Time removido com sucesso");
    }
}