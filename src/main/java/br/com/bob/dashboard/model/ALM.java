/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.enums.AlmStatus;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "ALM")
@EqualsAndHashCode
public class ALM implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ALM_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TITLE", nullable = false)
    @Getter @Setter private Integer title;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    @Getter @Setter private AlmStatus status;
    
    @ManyToOne
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID", nullable = false)
    @Getter @Setter private Ticket ticket;

    public ALM() {
        this.status = AlmStatus.OPEN;
    }

    public ALM(Integer title, Ticket ticket) {
        this.title = title;
        this.ticket = ticket;
        this.status = AlmStatus.OPEN;
    }
}