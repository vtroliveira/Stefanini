/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.model.ALM;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.repository.AlmDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RequestScoped
public class AlmService {
    @Inject private AlmDAO dao;
    
    public long countByTeam(final Team team) {
        return dao.findByTeam(team).count();
    }
    
    public List<ALM> getByTeam(final Team team) {
        return dao.findByTeam(team).getResultList();
    }
}
