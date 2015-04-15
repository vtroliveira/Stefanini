/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "LOGBOOK")
@EqualsAndHashCode
public class Logbook implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOGBOOK_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "DESCR", nullable = false)
    @Getter @Setter private String descr;
    
    @Lob
    @Column(name = "OBS")
    @Getter @Setter private String obs;
    
    @Column(name = "IMPACT", nullable = false)
    @Getter @Setter private String impact;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INSERT_IN", nullable = false)
    @Getter @Setter private Date insertIn;
    
    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    @Getter @Setter private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false)
    @Getter @Setter private Team team;
    
    public List<String> getObsBroken() {
        return obs != null ? Arrays.asList(obs.split("\n")) : Collections.EMPTY_LIST;
    }
}