/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.history.MetricHistory;
import br.com.bob.dashboard.model.history.ProductsHistory;
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
@Table(name = "PRODUCTS")
@EqualsAndHashCode(callSuper = true)
public class Products extends Metric implements Serializable {
    
    @Column(name = "PLAN", nullable = false)
    @Getter @Setter private int plan;
    
    @Column(name = "DONE", nullable = false)
    @Getter @Setter private int done;
    
    public String getPercentual() {
        final int zero = BigInteger.ZERO.intValue();
        if (plan > 0 && done > 0) {
            final int multiplier = 100;
            
            return new BigDecimal(done)
                    .multiply(new BigDecimal(multiplier))
                    .divide(new BigDecimal(plan), RoundingMode.DOWN)
                    .toString();
        }
        
        return String.valueOf(zero);
    }

    @Override
    public Class<? extends MetricHistory> historyClass() {
        return ProductsHistory.class;
    }
}