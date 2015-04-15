/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.ALM;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.AlmService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @RequestScoped
public class AlmBean extends ManagedBean {
    
    @Inject private AlmService service;

    public long countAlms(final Team team) {
        return service.countByTeam(team);
    }
    
    public List<ALM> getAlms(final Team team) {
        return service.getByTeam(team);
    }
}
