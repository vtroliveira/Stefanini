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
@Table(name = "SONAR_HISTORY")
@EqualsAndHashCode(callSuper = true)
public class SonarHistory extends MetricHistory implements Serializable {
    
    @Column(name = "BLOCKERS", nullable = false)
    @Getter @Setter private int blockers;
    
    @Column(name = "CRITICALS", nullable = false)
    @Getter @Setter private int criticals;
    
    @Column(name = "MAJORS", nullable = false)
    @Getter @Setter private int majors;
    
    @Column(name = "MINORS", nullable = false)
    @Getter @Setter private int minors;
    
    public int getBlockersAndCriticals() {
        return blockers + criticals;
    }
    
    public String getAsHtml() {
        final StringBuilder sb = new StringBuilder();
        sb.append(bold("Blockers: ")).append(blockers).append("<br/>")
          .append(bold("Criticals: ")).append(criticals).append("<br/>")
          .append(bold("Majors: ")).append(majors).append("<br/>")
          .append(bold("Minors: ")).append(minors).append("<br/>");
        
        return sb.toString();
    }
    
    private String bold(final String text) {
        return "<b>"+text+"</b>";
    }
}