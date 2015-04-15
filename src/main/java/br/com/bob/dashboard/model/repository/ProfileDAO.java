/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Permission;
import br.com.bob.dashboard.model.Profile;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface ProfileDAO extends EntityRepository<Profile, Long> {
    
    @Query(value = "SELECT p.permissions FROM Profile p WHERE p.id = ?1")
    public List<Permission> getPermissions(final Long profileId);
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    Profile findByNameEqual(final String name);
}