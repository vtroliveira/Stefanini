/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.enums;

import lombok.Getter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
public enum Status {
    Ok("Ok"),
    Nok("Nok"),
    Warn("Atenção");
   
    @Getter private final String label;

    private Status(String label) {
        this.label = label;
    }
}
