/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans.util;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
@ConversationScoped
public abstract class ConversationBean extends ManagedBean implements Serializable {
    @Getter private int step = 1;
    @Inject private Conversation conv;
    
    public String toStep(final int step, final String page) {
        this.step = step;
        return page;
    }
    
    protected void initConversation() {
        final FacesContext ctx = FacesContext.getCurrentInstance();
        
        if (ctx.isPostback() && conv.isTransient())
            conv.begin();
    }
    
    protected void endConversation() {
        if (!conv.isTransient())
            conv.end();
    }
}