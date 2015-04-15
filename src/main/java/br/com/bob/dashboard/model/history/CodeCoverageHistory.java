/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.history;

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
@Table(name = "CODE_COVERAGE_HISTORY")
@EqualsAndHashCode(callSuper = true)
public class CodeCoverageHistory extends MetricHistory implements Serializable {
    
    @Column(name = "COVERAGE_RATE", nullable = false)
    @Getter @Setter private double coverage;
}