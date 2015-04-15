/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import br.com.bob.dashboard.model.util.Portifolio;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "JOB")
@EqualsAndHashCode(of = "id")
public class Job implements Serializable, Portifolio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "JOB_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "TITLE", nullable = false)
    @Getter @Setter private String title;

    public Job() {}

    public Job(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    
    @Override
    public String getJQueryName() {
        return StringUtils.newStringUtf8(title.getBytes())
                          .replaceAll(" ", "_")
                          .toLowerCase();
    }
}