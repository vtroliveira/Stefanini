/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.enums.Phase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "TICKET")
@EqualsAndHashCode(of = {"id", "product", "phase", "team"})
public class Ticket implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TICKET_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "PRODUCT", nullable = false)
    @Getter @Setter private String product;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ACTUAL_PHASE", nullable = false)
    @Getter @Setter private Phase phase;
    
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "ticket")
    @Getter @Setter private List<ALM> alms;
    
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "ticket")
//    @Getter @Setter private List<UseCase> ucs;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "TICKET_TIMEBOX", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
               joinColumns = {@JoinColumn(name = "TICKET_ID")},
               inverseJoinColumns = {@JoinColumn(name = "TIMEBOX_DEF_ID")})
    @Getter @Setter private List<TimeboxDef> timeboxes;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID", nullable = false)
    @Getter @Setter private Team team;
    
//    public void addUseCase(final UseCase uc) {
//        if (ucs == null)
//            ucs = new ArrayList<>();
//        
//        ucs.add(uc);
//    }
    
    public void addALM(final ALM alm) {
        if (alms == null)
            alms = new ArrayList<>();
        
        alms.add(alm);
    }
    
    public boolean addTimebox(final TimeboxDef timebox) {
        if (timeboxes == null)
            timeboxes = new ArrayList<>();
        
        if (!timeboxes.isEmpty()) {
            TimeboxDef found = null;
            
            for (final TimeboxDef tb : timeboxes) {
                if (tb.getPhase().equals(timebox.getPhase())) {
                    found = tb;
                }
            }
            
            if (found != null)
                timeboxes.remove(found);
        }
        
        return timeboxes.add(timebox);
    }
    
    public TimeboxDef getSolution() {
        return filterTimebox(Phase.Solution);
    }
    
    public TimeboxDef getCoding() {
        return filterTimebox(Phase.Coding);
    }
    
    public TimeboxDef getTest() {
        return filterTimebox(Phase.Test);
    }
    
    public TimeboxDef getFinished() {
        return filterTimebox(Phase.Finished);
    }
    
    private TimeboxDef filterTimebox(final Phase phase) {
        for (TimeboxDef tb : timeboxes) {
            if (tb.getPhase().equals(phase))
                return tb;
        }
        return null;
    }
}