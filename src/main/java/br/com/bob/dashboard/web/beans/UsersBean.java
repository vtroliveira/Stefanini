/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.service.UserService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.Serializable;
import java.util.List;
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
public class UsersBean extends ManagedBean implements Serializable {
    private static final String PARAM_USER = "user";
    @Getter @Setter private User selected;
    @Inject private UserService service;
    
    public List<User> getAllUsers() {
        return service.getUsers();
    }
    
    public String updateProfile() {
        putParam(PARAM_USER, selected);
        return redirect("user_profile_edt");
    }
}