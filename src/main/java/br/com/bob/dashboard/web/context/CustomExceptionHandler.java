/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context;

import br.com.bob.dashboard.web.context.handler.ExceptionDispatcher;
import java.util.Iterator;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 *
 * @author Vitor
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
    private final ExceptionHandler wrapped;
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();
        while (i.hasNext()) {
            final ExceptionQueuedEvent event = i.next();
            final ExceptionQueuedEventContext context =
                    (ExceptionQueuedEventContext) event.getSource();
 
            final Throwable ex = context.getException();
            
            try {
                final ExceptionDispatcher dispatcher = 
                        BeanProvider.getContextualReference(ExceptionDispatcher.class);
                
                dispatcher.getEvent().fire(ex);
            } finally {
                i.remove();
            }
        }
    }
}