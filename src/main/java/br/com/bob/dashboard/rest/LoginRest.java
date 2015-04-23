/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.rest;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.User;
import br.com.bob.dashboard.service.UserService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.Response.Status;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RequestScoped
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginRest {
    @Inject private UserService service;
    
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public User get(User user) {
        try {
            return service.getUser(user.getLogin(), user.getPassword())
                          .withoutPassword();
        } catch (BusinessException e) {
            throw new WebApplicationException(e, Status.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Path("{user}")
    @GET
    public User get(@PathParam("user") String user) {
        try {
            return service.getUser(user).withoutPassword();
        } catch (BusinessException e) {
            e.printStackTrace(System.err);
            throw new WebApplicationException(e, Status.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}