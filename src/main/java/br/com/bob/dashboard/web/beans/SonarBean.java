/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Sonar;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.web.beans.util.MetricBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @RequestScoped
public class SonarBean extends MetricBean<Sonar> {
    @Getter @Setter private boolean showDetail;
    @Getter private Sonar selected;
    
    public void showMetrics(final Team team) {
        selected = getInfoByTeam(team);
        showDetail = true;
    }
}