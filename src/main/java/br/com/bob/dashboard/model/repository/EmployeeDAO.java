/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.model.Team;
import java.util.List;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author Vitor
 */
@Repository
public interface EmployeeDAO extends EntityRepository<Employee, Long> {
    
    List<Employee> findByTeamEqual(final Team team);
    
    @Query("select e from Employee e order by e.name")
    List<Employee> findAllOrderByName();
}