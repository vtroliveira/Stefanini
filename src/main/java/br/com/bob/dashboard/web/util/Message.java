/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor
 */
public final class Message {
    
    @Getter private final MessageType type;
    @Getter private final String title;
    @Getter @Setter private String detail;

    public Message(MessageType type, String title) {
        this.type = type;
        this.title = title;
    }

    public Message(MessageType type, String title, String detail) {
        this.type = type;
        this.title = title;
        this.detail = detail;
    }
    
    public void show() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        final ExternalContext ext = ctx.getExternalContext();
        final Flash flash = ext.getFlash();
        flash.put("message", this);
    }
}