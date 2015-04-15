/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context;

import javax.enterprise.event.Observes;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.deltaspike.jsf.api.listener.phase.AfterPhase;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseId;

/**
 *
 * @author Vitor
 */
public class UserLoggedListener {
    @Inject private UserLogged logged;
    
    public void listen(@Observes @AfterPhase(JsfPhaseId.RESTORE_VIEW) PhaseEvent event) {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final String page = getPage(ctx);
        
        try {
            if (page != null && !page.toLowerCase().contains("login")) {
                final String outcome = "login?faces-redirect=true";

                if (logged.isInactive()) {
                    logged.logout();

                    NavigationHandler nav = ctx.getApplication().getNavigationHandler();
                    nav.handleNavigation(ctx, null, outcome);
                    ctx.renderResponse();
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no listener de sessão ativa!");
            e.printStackTrace(System.err);
        }
    }
    
    private String getPage(final FacesContext ctx) {
        final HttpServletRequest request =
                (HttpServletRequest) ctx.getExternalContext().getRequest();
        
        final String path = request.getRequestURI();
        final int index = path.lastIndexOf("/");
        return path.substring(index);
    }
}