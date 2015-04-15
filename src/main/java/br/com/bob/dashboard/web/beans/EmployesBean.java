/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.service.EmployeeService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @RequestScoped
public class EmployesBean extends ManagedBean {
    private List<Employee> employes;
    @Getter @Setter private Employee selected;
    @Inject private EmployeeService service;
    
    public List<Employee> getEmployees() {
        if (employes == null)
            employes = service.getAll();
        
        return employes;
    }
    
    public void remove() {
        service.remove(selected);
        employes = null;
        info("Colaborador removido com sucesso");
    }
    
    public String edit() {
        putParam("employee", selected);
        return redirect("profile_cad");
    }
}