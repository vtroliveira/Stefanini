/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Permission;
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
public interface PermissionDAO extends EntityRepository<Permission, Long> {
    
    @Query(value = "SELECT DISTINCT p.toView FROM Permission p")
    List<String> getProtectedPages();
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    Permission findByNameEqual(String name);
}