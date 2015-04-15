/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.rest;

import br.com.bob.dashboard.model.Sonar;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.service.MetricService;
import br.com.bob.dashboard.service.TeamService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import static javax.ws.rs.core.Response.Status;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RequestScoped
@Path("sonar")
@Produces(MediaType.APPLICATION_JSON)
public class SonarRest {
    @Inject private MetricService<Sonar> metricService;
    @Inject private TeamService teamService;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void post(@NotNull @QueryParam("blockers") int blockers,
                     @NotNull @QueryParam("criticals") int criticals,
                     @NotNull @QueryParam("majors") int majors,
                     @NotNull @QueryParam("minors") int minors,
                     @NotNull @QueryParam("team") String team) {

        final Sonar sonar = new Sonar();
        sonar.setBlockers(blockers);
        sonar.setCriticals(criticals);
        sonar.setMajors(majors);
        sonar.setMinors(minors);
        
        final Team found = teamService.find(team);
        if (found == null)
            throw new WebApplicationException(Status.NOT_FOUND);
        
        sonar.setTeam(found);
        try {
            metricService.save(sonar);
        } catch (Exception e) {
            throw new WebApplicationException(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Sonar> get(@QueryParam("team") String team) {
        if (team == null)
            return getAll();
        else {
            final Team found = teamService.find(team);
            if (found != null) {
                final Sonar sonar = getOne(found);
                if (sonar != null)
                    return Arrays.asList(sonar);
            }
        }
            
        throw new WebApplicationException(Status.NOT_FOUND);
    }

    private List<Sonar> getAll() {
        final List<Sonar> sonar = new ArrayList<>();
        for (final Team t : teamService.getAllWithMetrics()) {
            final Sonar obj = getOne(t);
            if (obj != null) {
                sonar.add(obj);
            }
        }

        if (sonar.isEmpty()) {
            throw new WebApplicationException(Status.NOT_FOUND);
        } else {
            return sonar;
        }
    }
    
    private Sonar getOne(final Team team) {
        final Sonar sonar = metricService.findByTeam(Sonar.class, team);
        if (sonar != null) {
            return sonar;
        } else {
            return null;
        }
    }
}