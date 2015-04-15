/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.config;

import br.com.bob.dashboard.model.Permission;
import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.service.UserService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@Startup @Singleton
public class ConfigureBlockedPages {
    
    @Inject private UserService service;
    
    @PostConstruct
    public void blockedPages() {
        try {
            final Profile profile = service.newProfile(getAdmin());
            service.addPermission(buildPermission("VIEW_USR", "/user_config"), profile);
            service.addPermission(buildPermission("EDIT_USR_PROFILE", "/user_profile_edt"), profile);
            service.addPermission(buildPermission("VIEW_TEAMS", "/team_config"), profile);
            service.addPermission(buildPermission("EDIT_TEAMS", "/team_config_cad"), profile);
            service.addPermission(buildPermission("VIEW_JOBS", "/job_config"), profile);
            service.addPermission(buildPermission("EDIT_JOBS", "/job_config_cad"), profile);
            service.addPermission(buildPermission("REMOVE_EMPLOYEE", ""), profile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private Permission buildPermission(String name, String page) {
        final Permission p = new Permission();
        p.setName(name);
        p.setToView(page);
        return p;
    }
    
    private Profile getAdmin() {
        final Profile p = new Profile();
        p.setName("ADMIN");
        return p;
    }
}