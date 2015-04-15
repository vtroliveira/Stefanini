/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.history.CodeCoverageHistory;
import br.com.bob.dashboard.model.history.MetricHistory;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Entity
@Table(name = "CODE_COVERAGE")
@EqualsAndHashCode(callSuper = true)
public class CodeCoverage extends Metric implements Serializable {
    
    @Column(name = "COVERAGE_RATE", nullable = false)
    @Getter @Setter private double coverage;

    @Override
    public Class<? extends MetricHistory> historyClass() {
        return CodeCoverageHistory.class;
    }
}