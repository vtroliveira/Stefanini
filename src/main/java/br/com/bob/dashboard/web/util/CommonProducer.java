/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.util;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.repository.PermissionDAO;
import br.com.bob.dashboard.service.TeamService;
import br.com.bob.dashboard.web.qualifiers.AllTeams;
import br.com.bob.dashboard.web.qualifiers.BlockedPages;
import br.com.bob.dashboard.web.qualifiers.Teams;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author Vitor
 */
public class CommonProducer implements Serializable {
    
    @Produces @BlockedPages @ApplicationScoped
    public List<String> getBlockedPages(PermissionDAO dao) {
        return dao.getProtectedPages();
    }
    
    @Produces @Teams @ApplicationScoped
    public List<Team> getTeams(TeamService service) {
        return service.getAllWithMetrics();
    }
    
    @Produces @AllTeams @ApplicationScoped
    public List<Team> getAllTeams(TeamService service) {
        return service.getAll();
    }
}