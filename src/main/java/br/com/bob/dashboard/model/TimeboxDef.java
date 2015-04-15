/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.enums.Phase;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Entity
@Table(name = "TIMEBOX_DEF")
@EqualsAndHashCode(of = {"number", "phase"})
public class TimeboxDef implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TIMEBOX_DEF_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TIMEBOX_NUMBER", nullable = false)
    @Getter @Setter private Integer number;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "PHASE", nullable = false)
    @Getter @Setter private Phase phase;
}