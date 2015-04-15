/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.model.Job;
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
public interface JobDAO extends EntityRepository<Job, Long> {

    @Query(singleResult = SingleResultType.OPTIONAL)
    Job findByTitleEqual(String title);
    
    @Query("select e from Employee e where e.job = ?1")
    List<Employee> getEmployees(Job job);
}