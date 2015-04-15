/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "EMPLOYEE")
@EqualsAndHashCode(of = "id")
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMPLOYEE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "NAME", nullable = false)
    @Getter @Setter private String name;
    
    @Column(name = "INFO")
    @Getter @Setter private String info;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false)
    @Getter @Setter private Team team;
    
    @ManyToOne
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID", nullable = false)
    @Getter @Setter private Job job;
    
    public String getDataOption() {
        final StringBuilder sb = new StringBuilder();
        if (team != null)
            sb.append(team.getJQueryName()).append(" ");
            
        if (job != null)
            sb.append(job.getJQueryName());
        
        return sb.toString();
    }

    public Employee() {}

    public Employee(Long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }
    
    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}