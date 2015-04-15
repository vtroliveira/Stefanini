/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.service.UserService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class UsrProfileBean extends ManagedBean implements Serializable {
    private static final String PARAM_USER = "user";
    private static final String PAGE_USERS = "user_config";
    @Getter private User user;
    
    @Inject private UserService service;
    
    @PostConstruct
    public void init() {
        user = getParam(PARAM_USER, User.class);
        if (user == null) {
            error("Por favor, escolha um usuário");
            toPage(PAGE_USERS, true);
        } else if (user.getProfile() == null)
            user.setProfile(new Profile());
    }
    
    public List<Profile> getProfiles() {
        return service.getProfiles();
    }
    
    public String update() {
        service.updateProfile(user, user.getProfile().getId());
        info("Usuário: " + user.getLogin() + " atualizado com sucesso");
        return redirect(PAGE_USERS);
    }
}
