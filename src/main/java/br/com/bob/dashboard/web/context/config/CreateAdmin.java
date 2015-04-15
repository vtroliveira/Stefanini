/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.config;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.model.enums.ActiveStatus;
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
public class CreateAdmin {
    @Inject private UserService usrService;
    
    @PostConstruct
    public void init() {
        try {
            createAdminUser();
            createUserProfile();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void createAdminUser() {
        try {
            final User usr = getUser();
            usr.setProfile(getAdminProfile());
            
            if (!usrService.exists(usr.getLogin()))
                usrService.newUser(usr);
        } catch (BusinessException e) {
            System.out.println("Erro ao criar admin");
            e.printStackTrace(System.err);
        }
    }
    
    private User getUser() {
        final User user = new User();
        user.setLogin("admin");
        user.setPassword("qwe123!@#");
        user.setEmail("");
        user.setStatus(ActiveStatus.ACTIVE);
        return user;
    }
    
    private Profile getAdminProfile() {
        return createProfile("ADMIN");
    }
    
    private Profile createUserProfile() {
        return createProfile("COMMON_USR");
    }
    
    private Profile createProfile(String name) {
        final Profile p = new Profile();
        p.setName(name);
        return usrService.newProfile(p);
    }
}