/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context;

import br.com.bob.dashboard.web.qualifiers.BlockedPages;
import java.util.List;
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
public class PagePermissionListener {
    @Inject private UserLogged logged;
    @Inject @BlockedPages private List<String> blocked;
    
    public void listen(@Observes @AfterPhase(JsfPhaseId.RESTORE_VIEW) PhaseEvent event) {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final String page = getPage(ctx).replace(".xhtml", "").trim();
        
        if (logged.isActive() && isBlocked(page)) {
            final String outcome = "denied?faces-redirect=true";

            if (!logged.hasPagePermission(page)) {
                NavigationHandler nav = ctx.getApplication().getNavigationHandler();
                nav.handleNavigation(ctx, null, outcome);
                ctx.renderResponse();
            }
        }
    }
    
    private boolean isBlocked(final String page) {
        if (page != null && !blocked.isEmpty())
            return blocked.contains(page);
        else
            return false;
    }
    
    private String getPage(final FacesContext ctx) {
        final HttpServletRequest request =
                (HttpServletRequest) ctx.getExternalContext().getRequest();
        
        final String path = request.getRequestURI();
        final int index = path.lastIndexOf("/");
        return path.substring(index);
    }
}