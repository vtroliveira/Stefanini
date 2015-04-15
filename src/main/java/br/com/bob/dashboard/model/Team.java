/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.enums.BooleanChar;
import br.com.bob.dashboard.model.util.Portifolio;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.StringUtils;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "TEAM")
@EqualsAndHashCode(of = "id")
public class Team implements Serializable, Portifolio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "NAME", nullable = false)
    @Getter @Setter private String name;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "HAS_METRICS", nullable = false, length = 1)
    @Getter @Setter private BooleanChar metrics;

    public Team() {}

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getJQueryName() {
        return StringUtils.newStringUtf8(name.getBytes())
                          .replaceAll(" ", "_")
                          .toLowerCase();
    }
}