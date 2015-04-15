/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.Ticket;
import br.com.bob.dashboard.model.enums.Phase;
import br.com.bob.dashboard.service.TicketService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.Serializable;
import java.util.Arrays;
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
public class TicketBean extends ManagedBean implements Serializable {
    private final String ticketParam = "ticket";
    @Getter @Setter private Ticket selected;
//    @Getter @Setter private boolean showUcs;
    @Getter @Setter private boolean showALMs;
    @Getter private List<Ticket> tickets;
    @Inject private TicketService service;
    
    @PostConstruct
    public void init() {
        selected = new Ticket();
//        showUcs = false;
        showALMs = false;
        page = 0;
        max = 5;
        search(false);
    }
    
    public List<Phase> getPhases() {
        return Arrays.asList(Phase.values());
    }
    
    public String edit() {
        putParam(ticketParam, selected);
        return "ticket_cad?faces-redirect=true";
    }
    
    public void remove() {
        service.remove(selected);
        search(false);
        info("Produto removido com sucesso");
        selected = new Ticket();
    }
    
    public void next() {
        page += max;
        search(true);
    }
    
    public void prev() {
        page = page >= max ? page - max : 0;
        search(true);
    }
    
    public void doSearch() {
        page = 0;
        search(true);
    }
    
    private void search(final boolean showEmptyMessage) {
        final Team team = selected.getTeam();
        final Phase phase = selected.getPhase();
        
        if (team == null && phase == null)
            tickets = service.getTickets(page, max);
        else if (team != null && phase != null)
            tickets = service.getTickets(team, phase, page, max);
        else 
            tickets = service.getTicketsByTeamOrPhase(team, phase, page, max);
        
        if (tickets.isEmpty() && showEmptyMessage)
            warn("Não existem tickets para serem exibidos");
    }
}