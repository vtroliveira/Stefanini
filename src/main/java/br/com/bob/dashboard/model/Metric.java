/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.history.MetricHistory;
import br.com.bob.dashboard.web.util.Utils;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.joda.time.DateTime;
import org.joda.time.Period;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@MappedSuperclass
public abstract class Metric {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Getter @Setter private Long id;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @Getter @Setter protected User user;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    @Getter @Setter protected Team team;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_UPDATE", nullable = false)
    @Getter @Setter protected Date date;

    public Metric() {}
    
    public String getInactiveTime() {
        final DateTime old = new DateTime(date);
        final DateTime now = new DateTime();
        final Period period = new Period(old, now);
        
        int interval;
        if ((interval = period.getDays()) > 0)
            return getText(interval, "dia(s)");
        
        else if ((interval = period.getHours()) > 0)
            return getText(interval, "hora(s)");
        
        else if ((interval = period.getMinutes()) > 0)
            return getText(interval, "minuto(s)");
        else
            return "há pouco";
    }
    
    private String getText(Integer interval, String label) {
        final String text = "{0} {1}";
        return text.replace("{0}", interval.toString())
                       .replace("{1}", label);
    }
    
    public abstract Class<? extends MetricHistory> historyClass();
    
    protected Utils getUtils() {
        return BeanProvider.getContextualReference(Utils.class);
    }
}