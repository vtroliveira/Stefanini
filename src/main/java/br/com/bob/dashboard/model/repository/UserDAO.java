/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.apache.deltaspike.data.api.SingleResultType;

/**
 *
 * @author Vitor
 */
@Repository
public interface UserDAO extends EntityRepository<User, Long> {
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    User findByLoginEqualAndPasswordEqual(final String login, final String password);
    
    @Query(singleResult = SingleResultType.OPTIONAL)
    User findByLoginEqual(final String login);
}