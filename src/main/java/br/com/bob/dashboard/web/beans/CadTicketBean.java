/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.ALM;
import br.com.bob.dashboard.model.Ticket;
import br.com.bob.dashboard.model.TimeboxDef;
import br.com.bob.dashboard.model.UseCase;
import br.com.bob.dashboard.service.TicketService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class CadTicketBean extends ManagedBean implements Serializable {
    private final String ticketParam = "ticket";
    
    @Getter @Setter private Ticket selected;
    @Getter @Setter private ALM alm;
//    @Getter @Setter private UseCase uc;
    @Getter @Setter private TimeboxDef tb;
    @Getter @Setter private boolean newUseCase;
    @Getter @Setter private boolean newAlm;
    @Inject private TicketService service;
    
    @PostConstruct
    public void init() {
        alm = new ALM();
//        uc = new UseCase();
        tb = new TimeboxDef();
        loadSelected();
    }
    
    private void loadSelected() {
        selected = getParam(ticketParam, Ticket.class);
        if (selected == null)
            selected = new Ticket();
        else {
            tb.setPhase(selected.getPhase());
            switch (selected.getPhase()) {
                case Solution : tb.setNumber(getTbNumber(selected.getSolution())); break;
                case Coding : tb.setNumber(getTbNumber(selected.getCoding())); break;
                case Test : tb.setNumber(getTbNumber(selected.getTest())); break;
                case Finished : tb.setNumber(getTbNumber(selected.getFinished())); break;
            }
        }
    }
    
    private int getTbNumber(final TimeboxDef tb) {
        return tb != null ? tb.getNumber() : 0;
    }
    
    public String save() {
        service.create(selected, tb);
        info("Produto cadastrado com sucesso");
        return redirect("tickets");
    }
    
//    public void addUseCase() {
//        uc.setTicket(selected);
//        selected.addUseCase(uc);
//        uc = new UseCase();
//    }
    
    public void addAlm() {
        alm.setTicket(selected);
        selected.addALM(alm);
        alm = new ALM();
    }
    
//    public void removeUseCase() {
//        final List<UseCase> ucs = selected.getUcs();
//        ucs.remove(uc);
//        uc = new UseCase();
//    }
    
    public void removeAlm() {
        final List<ALM> alms = selected.getAlms();
        alms.remove(alm);
        alm = new ALM();
    }
}