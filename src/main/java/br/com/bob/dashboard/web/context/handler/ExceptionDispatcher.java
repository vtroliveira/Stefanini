/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.handler;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import lombok.Getter;

/**
 *
 * @author Vitor
 */
public class ExceptionDispatcher {
    
    @Inject @Any @Getter private Event<Throwable> event;
}