/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.enums.Status;
import br.com.bob.dashboard.model.history.EnterpriseArchitectHistory;
import br.com.bob.dashboard.model.history.MetricHistory;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Entity
@Table(name = "ENTERPRISE_ARCHITECT")
@EqualsAndHashCode(callSuper = true)
public class EnterpriseArchitect extends Metric implements Serializable {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    @Getter @Setter private Status status;

    @Override
    public Class<? extends MetricHistory> historyClass() {
        return EnterpriseArchitectHistory.class;
    }
}