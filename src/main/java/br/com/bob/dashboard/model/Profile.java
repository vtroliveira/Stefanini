/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name = "PROFILE")
@EqualsAndHashCode
public class Profile implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROFILE_ID")
    @Getter @Setter private Long id;
    
    @Column(name = "NAME", nullable = false)
    @Getter @Setter private String name;
    
    @ManyToMany
    @JoinTable(name = "PROFILE_PERMISSION",
               joinColumns = {@JoinColumn(name = "PERMISSION_ID", nullable = false)},
               inverseJoinColumns = {@JoinColumn(name = "PROFILE_ID", nullable = false)})
    @Getter @Setter private List<Permission> permissions;
    
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(name); 
    }
    
    public void addPermission(final Permission permission) {
        if (permissions == null)
            permissions = new ArrayList<>();
        
        permissions.add(permission);
    }
}