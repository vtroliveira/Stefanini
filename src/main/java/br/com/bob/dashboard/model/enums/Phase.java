/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.model.enums;

import lombok.Getter;

/**
 *
 * @author Vitor
 */
public enum Phase {
    Solution("solution"),
    Coding("coding"),
    Test("test"),
    Finished("finished");
    
    @Getter private final String jquery;

    private Phase(String jquery) {
        this.jquery = jquery;
    }
}