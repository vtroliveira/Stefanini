/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.history;

import br.com.bob.dashboard.model.*;
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
@Table(name = "USE_CASE")
@EqualsAndHashCode
public class UseCaseHistory implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USE_CASE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TITLE", nullable = false)
    @Getter @Setter private String title;
    
    @ManyToOne
    @JoinColumn(name = "TICKET_ID", referencedColumnName = "TICKET_ID", nullable = false)
    @Getter @Setter private Ticket ticket;

    public UseCaseHistory() {}

    public UseCaseHistory(String title, Ticket ticket) {
        this.title = title;
        this.ticket = ticket;
    }
}