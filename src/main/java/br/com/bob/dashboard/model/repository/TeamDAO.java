/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.repository;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.enums.BooleanChar;
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
public interface TeamDAO extends EntityRepository<Team, Long> {

    @Query(singleResult = SingleResultType.OPTIONAL)
    Team findByNameEqual(String name);
    
    @Query("select e from Employee e where e.team = ?1")
    List<Employee> getEmployees(Team team);
    
    @Query("select t from Team t order by t.name")
    List<Team> findAllOrderByName();
    
    @Query("select t from Team t where t.metrics = ?1 order by t.name")
    List<Team> findByMetricsOrderByName(final BooleanChar hasMetrics);
    
    @Query("select s, cc, ea, cr, cdr, p, tc, a "
            + "from Sonar s, CodeCoverage cc, EnterpriseArchitect ea, "
            + "ClientReview cr, CodeReview cdr, Products p, TestCase tc, "
            + "ALM a "
            + "where s.team = ?1 "
            + "or cc.team = ?1 "
            + "or ea.team = ?1 "
            + "or cr.team = ?1 "
            + "or cdr.team = ?1 "
            + "or p.team = ?1 "
            + "or tc.team = ?1 "
            + "or a.ticket.team = ?1")
    List getMetrics(Team team);
}