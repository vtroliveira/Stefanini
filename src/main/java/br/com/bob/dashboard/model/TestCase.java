/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.history.MetricHistory;
import br.com.bob.dashboard.model.history.TestCaseHistory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
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
@Table(name = "TEST_CASE")
@EqualsAndHashCode(callSuper = true)
public class TestCase extends Metric implements Serializable {
    
    @Column(name = "TOTAL", nullable = false)
    @Getter @Setter private int total;
    
    @Column(name = "ERROR", nullable = false)
    @Getter @Setter private int error;
    
    public int getPercentual() {
        final int zero = BigInteger.ZERO.intValue();
        if (total > 0 && error > 0) {
            final int multiplier = 100;
            
            return new BigDecimal(error)
                    .multiply(new BigDecimal(multiplier))
                    .divide(new BigDecimal(total), RoundingMode.DOWN)
                    .intValue();
        }
        
        return zero;
    }

    @Override
    public Class<? extends MetricHistory> historyClass() {
        return TestCaseHistory.class;
    }
}