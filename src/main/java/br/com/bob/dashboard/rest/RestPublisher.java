/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@ApplicationPath("rest")
public class RestPublisher extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set set = new HashSet<>();
        set.add(SonarRest.class);
        return set;
    }
}
