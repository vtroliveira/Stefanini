/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Logbook;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.LogbookService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
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
public class LogbookBean extends ManagedBean implements Serializable {
    private final String teamParam = "team";
    private final String logParam = "logbook";
    @Getter private Team team;
    @Getter private List<Logbook> logbooks;
    @Getter @Setter private Logbook selected;
    @Inject private LogbookService service;
    
    @PostConstruct
    public void init() {
        page = 0;
        max = 5;
        if (team == null)
            team = getParam(teamParam, Team.class);
        
        logbooks = new ArrayList<>();
        search(false);
    }
    
    public void remove() {
        service.remove(selected);
        search(false);
        info("Diário de bordo removido com sucesso");
    }
    
    public String edit() {
        putParam(logParam, selected);
        return redirect("logbook_cad");
    }
    
    public void next() {
        page += max;
        search(true);
    }
    
    public void prev() {
        page = page >= max ? page - max : 0;
        search(true);
    }
    
    private void search(final boolean showEmptyMessage) {
        final List<Logbook> result = service.getLogbooks(team, page, max);
        if (result == null || result.isEmpty()) {
            if (showEmptyMessage)
                warn("Não há mais diários de bordo cadastrados para a frente: " + team.getName());
        } else {
            logbooks.clear();
            logbooks.addAll(result);
        }
    }
}