/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Products;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.AlmService;
import br.com.bob.dashboard.service.TicketService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import br.com.bob.dashboard.web.beans.util.MetricTable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @ViewScoped
public class IndexBean extends ManagedBean implements Serializable {
    private final String teamParam = "team";
    @Getter @Setter private MetricTable selected;
    @Getter private List<MetricTable> metrics;
    
    @Inject private TeamBean teams;
    @Inject private SonarBean sonar;
    @Inject private CodeCoverageBean coverage;
    @Inject private EnterpriseArchitectBean ea;
    @Inject private CodeReviewBean codeReview;
    @Inject private ClientReviewBean clientReview;
    @Inject private TimeboxBean timebox;
    @Inject private AlmService service;
    @Inject private TestCaseBean test;
    @Inject private TicketService product;
    
    @PostConstruct
    public void init() {
        metrics = new ArrayList<>();
        
        for (Team team : teams.getTeams()) {
            final MetricTable m = new MetricTable();
            m.setTeam(team);
            m.setSonar(sonar.getInfoByTeam(team));
            m.setCoverage(coverage.getInfoByTeam(team));
            m.setEa(ea.getInfoByTeam(team));
            m.setCodeReview(codeReview.getInfoByTeam(team));
            m.setClientReview(clientReview.getInfoByTeam(team));
            m.setTimebox(timebox.getInfoByTeam(team));
            m.setAlms(service.getByTeam(team));
            m.setTestCase(test.getInfoByTeam(team));
            
            final Products p = new Products();
            p.setPlan((int) product.countTotalTickets(team));
            p.setDone((int) product.inTest(team));
            m.setProduct(p);
            
            metrics.add(m);
        }
    }
    
    public String toLogbook() {
        putParam(teamParam, selected.getTeam());
        return redirect("logbook");
    }
}