/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Permission;
import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.model.enums.ActiveStatus;
import br.com.bob.dashboard.model.repository.PermissionDAO;
import br.com.bob.dashboard.model.repository.ProfileDAO;
import br.com.bob.dashboard.model.repository.UserDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class UserService {
    
    @Inject private UserDAO userDAO;
    @Inject private ProfileDAO profileDAO;
    @Inject private PermissionDAO permissionDAO;
    
    public void newUser(final User user) {
        if (exists(user.getLogin()))
            throw new BusinessException("Usuário já existente");
        else {
            try {
                user.setStatus(ActiveStatus.ACTIVE);
                userDAO.saveAndFlush(user);
            } catch (Exception e) {
                throw new BusinessException("Erro ao criar usuário", e);
            }
        }
    }
    
    public void update(final User user) {
        try {
            userDAO.saveAndFlush(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar usuário", e);
        }
    }
    
    public void updateProfile(final User user, final long profileId) {
        try {
            user.setProfile(getProfile(profileId));
            update(user);
        } catch (Exception e) {
            throw new BusinessException("Erro ao atualizar usuário", e);
        }
    }
    
    public User getUser(final String login, final String password) {
        final String encoded = DigestUtils.sha1Hex(password);
        return getUserEncryptedPwd(login, encoded);
    }
    
    public User getUserEncryptedPwd(final String login, final String encoded) {
        final User get = userDAO.findByLoginEqualAndPasswordEqual(login, encoded);
        if (get != null)
            return get;
        else
            throw new BusinessException("Usuário não encontrado");
    }
    
    public boolean exists(final String login) {
        return userDAO.findByLoginEqual(login) != null;
    }
    
    @Transactional
    public Profile newProfile(final Profile p) {
        final Profile found = getProfile(p.getName());
        if (found == null)
            return profileDAO.save(p);
        else
            return found;
    }
    
    @Transactional
    public void addPermission(Permission permission, Profile profile) {
        final Profile base = profileDAO.findBy(profile.getId());
        if (base == null)
            throw new BusinessException("Perfil inválido para associação");
        
        permission = newPermission(permission);
        
        final List<Permission> permissions = profileDAO.getPermissions(profile.getId());
        if (!permissions.contains(permission)) {
            profile.addPermission(newPermission(permission));
            profileDAO.save(base);
        }
    }
    
    @Transactional
    public Permission newPermission(Permission permission) {
        final Permission found = permissionDAO.findByNameEqual(permission.getName());
        if (found != null)
            return found;
        else
            return permissionDAO.save(permission);
    }
    
    public Profile getProfile(String name) {
        return profileDAO.findByNameEqual(name);
    }
    
    public Profile getProfile(final long id) {
        return profileDAO.findBy(id);
    }
    
    public List<Profile> getProfiles() {
        return profileDAO.findAll();
    }
    
    public List<User> getUsers() {
        return userDAO.findAll();
    }
    
    public User getUser(final String login) {
        final User found = userDAO.findByLoginEqual(login);
        if (found == null)
            throw new BusinessException("Usuário não encontrado");
        
        return found;
    }
}