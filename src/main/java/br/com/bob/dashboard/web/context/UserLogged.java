/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context;

import br.com.bob.dashboard.model.Permission;
import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.model.repository.ProfileDAO;
import br.com.bob.dashboard.web.qualifiers.LoggedIn;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vitor
 */
@Named @SessionScoped
public class UserLogged implements Serializable {
    
    @Inject private ProfileDAO dao;
    private User user;
    private List<Permission> permissions;
    
    @Produces @LoggedIn @Named("logged")
    public User getUser() {
        return user;
    }
    
    public void login(final User user) {
        this.user = user;
        loadPermissions();
    }
    
    public void logout() {
        this.user = null;
        invalidate();
    }
    
    public boolean isActive() {
        return getUser() != null;
    }
    
    public boolean isInactive() {
        return !isActive();
    }
    
    public boolean hasPermission(final String name) {
        if (permissions != null) {
            for (Permission p : permissions) {
                if (name.equals(p.getName()))
                    return true;
            }
        }
        return false;
    }
    
    public boolean hasPagePermission(final String page) {
        if (permissions != null) {
            for (Permission p : permissions) {
                if (page.equals(p.getToView()))
                    return true;
            }
        }
        return false;
    }
    
    private void loadPermissions() {
        final Profile p = user.getProfile();        
        if (p != null)
            permissions = dao.getPermissions(p.getId());
        else
            permissions = new ArrayList<>();
    }
    
    private void invalidate() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();

        try {
            ext.invalidateSession();
        } catch (Exception e) {
            System.err.println("Erro ao invalidar sessão: " + e.getMessage());
        }
    }
    
    @PreDestroy
    public void dispose() {
        this.user = null;
    }
}