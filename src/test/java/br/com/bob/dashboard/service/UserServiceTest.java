/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Profile;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.model.enums.ActiveStatus;
import javax.inject.Inject;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;
import org.apache.deltaspike.testcontrol.api.TestControl;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RunWith(CdiTestRunner.class)
@TestControl(projectStage = ProjectStage.UnitTest.class)
public class UserServiceTest {
    @Inject private UserService service;
    
    @Test
    public void newProfile() {
        final Profile p = buildProfile();
        try {
            Assert.assertNotNull(service.newProfile(p));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void newUserWithoutProfile() {
        try {
            service.newUser(buildUser());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void existingUser() {
        final User user = buildUser();
        try {
            service.newUser(user);
            Assert.fail("Criado dois usuários com o mesmo login");
        } catch (BusinessException e) {
            Assert.assertEquals("Usuário já existente", e.getMessage());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void updateUserProfile() {
        final User user = buildUser();
        try {
            final Profile p = service.newProfile(buildProfile());
            service.updateProfile(user, p.getId());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    
    private User buildUser() {
        final User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setStatus(ActiveStatus.ACTIVE);
        return user;
    }
    
    private Profile buildProfile() {
        final Profile p = new Profile();
        p.setName("test");
        return p;
    }
}
