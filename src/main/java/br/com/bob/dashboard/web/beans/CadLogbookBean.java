/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.Employee;
import br.com.bob.dashboard.model.Logbook;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.EmployeeService;
import br.com.bob.dashboard.service.LogbookService;
import br.com.bob.dashboard.service.TeamService;
import br.com.bob.dashboard.web.beans.util.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Named @ViewScoped
public class CadLogbookBean extends ManagedBean implements Serializable {
    private final String teamParam = "team";
    private final String logParam = "logbook";
    @Getter @Setter private Logbook log;
    @Getter @Setter private List<Employee> employees;
    @Inject private EmployeeService empService;
    @Inject private TeamService teamService;
    @Inject private LogbookService logService;
    
    @PostConstruct
    public void init() {
        log = getParam(logParam, Logbook.class);
        if (log == null) {
            log = new Logbook();
            loadFirstEmployees();
        } else {
            fillEmployees(log.getTeam());
        }
    }
    
    public void teamChange(ValueChangeEvent e) {
        employees.clear();
        final Object selected = e.getNewValue();
        if (selected != null) {
            employees.addAll(empService.getByTeam((Team) selected));
        }
    }
    
    public String save() {
        logService.save(log);
        info("Diário de bordo inserido com sucesso");
        
        putParam(teamParam, log.getTeam());
        return "logbook?faces-redirect=true";
    }
    
    private void loadFirstEmployees() {
        final List<Team> teams = teamService.getAll();
        if (teams.isEmpty()) {
            warn("Não há times cadastrados", "Não é possível inserir diário de bordo");
        } else {
            final Team first = teams.get(0);
            fillEmployees(first);
        }
    }
    
    private void fillEmployees(final Team team) {
        employees = new ArrayList<>();
        employees.addAll(empService.getByTeam(team));
    }
}