/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.handler;

import br.com.bob.dashboard.web.util.Message;
import br.com.bob.dashboard.web.util.MessageType;
import javax.enterprise.event.Observes;

/**
 * 
 * @author Vitor <vtr.oliveira at Nerv>
 */
public abstract class ContextExceptionHandler {
    
    public void handle(@Observes Throwable cause) {
        while(cause != null && !type().isInstance(cause)) {
            cause = cause.getCause();
        }
        
        if (cause != null) {
            final String title = getTranslateMessage(cause);
            final Message msg = new Message(MessageType.ERROR, title);
            msg.show();
            
            if (showStackTrace())
                cause.printStackTrace(System.err);
        }
    }
    
    protected abstract Class<? extends Throwable> type();
    
    protected String getTranslateMessage(final Throwable exception) {
        return exception.getMessage();
    }
    
    protected abstract boolean showStackTrace();
}