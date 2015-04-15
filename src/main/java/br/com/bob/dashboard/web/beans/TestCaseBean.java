/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.TestCase;
import br.com.bob.dashboard.service.TeamService;
import br.com.bob.dashboard.web.beans.util.MetricBean;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @RequestScoped
public class TestCaseBean extends MetricBean<TestCase> {
    
    @Inject private TeamService teamService;
    
    public int getTotal() {
        final List<Team> teams = teamService.getAll();
        int size = 0;
        
        if (!teams.isEmpty()) {
            int sum = 0;
            
            for (Team team : teams) {
                final TestCase tc = getInfoByTeam(team);
                
                if (tc != null) {
                    sum += tc.getPercentual();
                    size++;
                }
            }
        
            if (size > 0) {
                return new BigDecimal(sum)
                            .divide(new BigDecimal(size), RoundingMode.DOWN)
                            .intValue();
            }
        }
            
        return size;
    }
}