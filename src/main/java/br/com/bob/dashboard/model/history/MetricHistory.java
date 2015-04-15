/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.history;

import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.User;
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

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@MappedSuperclass
public abstract class MetricHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @Getter @Setter private Long id;
    
    @Column(name = "PARENT_TABLE_ID")
    @Getter @Setter protected Long parentId;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @Getter @Setter protected User user;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_INSERT", nullable = false)
    @Getter @Setter protected Date date;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
    @Getter @Setter protected Team team;

    public MetricHistory() {}
    
    protected Utils getUtils() {
        return BeanProvider.getContextualReference(Utils.class);
    }
}