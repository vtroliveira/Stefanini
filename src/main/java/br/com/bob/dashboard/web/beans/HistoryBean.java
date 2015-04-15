/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.history.ClientReviewHistory;
import br.com.bob.dashboard.model.history.CodeCoverageHistory;
import br.com.bob.dashboard.model.history.CodeReviewHistory;
import br.com.bob.dashboard.model.history.EnterpriseArchitectHistory;
import br.com.bob.dashboard.model.history.ProductsHistory;
import br.com.bob.dashboard.model.history.SonarHistory;
import br.com.bob.dashboard.model.history.TestCaseHistory;
import br.com.bob.dashboard.model.history.TimeboxHistory;
import br.com.bob.dashboard.service.HistoryService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import br.com.bob.dashboard.web.beans.util.MetricTableHistory;
import br.com.bob.dashboard.web.util.Utils;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @ViewScoped
public class HistoryBean extends ManagedBean implements Serializable {
    @Getter @Setter private Team team;
    @Getter @Setter private String date;
    @Getter private List<MetricTableHistory> metrics;
    @Getter private List<String> weekDates;
    @Inject private HistoryService service;
    @Inject private Utils utils;
    
    @PostConstruct
    public void init() {
        metrics = new ArrayList<>();
        buildWeeks();
    }
    
    public void show() {
        metrics.clear();
        metrics.add(build());
        
        if (metrics.isEmpty())
            warn("Sua pesquisa não retornou resultados");
    }
    
    private void buildWeeks() {
        final DateTime dt = new DateTime();
        
        weekDates = new ArrayList<>();
        weekDates.add(utils.onlyDate(dt.toDate()));
        for (int i = 1; i < 7; i++) {
            final String result = utils.onlyDate(dt.minusDays(i).toDate());
            weekDates.add(result);
        }
    }
    
    private MetricTableHistory build() {
        final MetricTableHistory tb = new MetricTableHistory();
        tb.setSonar(build(SonarHistory.class));
        tb.setCoverage(build(CodeCoverageHistory.class));
        tb.setProduct(build(ProductsHistory.class));
        tb.setEa(build(EnterpriseArchitectHistory.class));
        tb.setClientReview(build(ClientReviewHistory.class));
        tb.setCodeReview(build(CodeReviewHistory.class));
        tb.setTimebox(build(TimeboxHistory.class));
        tb.setTestCase(build(TestCaseHistory.class));
        return tb;
    }
    
    private <T> T build(Class<T> type) {
        try {
            final Date dt = utils.onlyDate(date);
            return service.getHistory(type, team, dt);
        } catch (ParseException e) {
            error("Erro ao encontrar histórico", e.getMessage());
            return null;
        }
    }
}