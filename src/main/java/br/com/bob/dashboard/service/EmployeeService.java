/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.repository.EmployeeDAO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class EmployeeService {
    
    @Inject private EmployeeDAO dao;
    @Inject private EntityManager manager;
    
    @Transactional
    public Employee create(final Employee employee) {
        try {
            return dao.save(employee);
        } catch (Exception e) {
            throw new BusinessException("Erro ao inserir colaborador", e);
        }
    }
    
    @Transactional
    public void remove(Employee employee) {
        try {
            employee = manager.getReference(Employee.class, employee.getId());
            dao.remove(employee);
        } catch (Exception e) {
            throw new BusinessException("Erro ao remover o colaborador", e);
        }
    }
    
    public List<Employee> getAll() {
        return dao.findAllOrderByName();
    }
    
    public List<Employee> getByTeam(final Team team) {
        return dao.findByTeamEqual(team);
    }
}
