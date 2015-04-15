/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans.util;

import br.com.bob.dashboard.model.ALM;
import br.com.bob.dashboard.model.ClientReview;
import br.com.bob.dashboard.model.CodeCoverage;
import br.com.bob.dashboard.model.CodeReview;
import br.com.bob.dashboard.model.EnterpriseArchitect;
import br.com.bob.dashboard.model.Products;
import br.com.bob.dashboard.model.Sonar;
import br.com.bob.dashboard.model.Team;
import br.com.bob.dashboard.model.TestCase;
import br.com.bob.dashboard.model.Timebox;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
public class MetricTable {
    
    @Getter @Setter private Team team;
    @Getter @Setter private Sonar sonar;
    @Getter @Setter private CodeCoverage coverage;
    @Getter @Setter private Products product;
    @Getter @Setter private EnterpriseArchitect ea;
    @Getter @Setter private CodeReview codeReview;
    @Getter @Setter private ClientReview clientReview;
    @Getter @Setter private Timebox timebox;
    @Getter @Setter private List<ALM> alms;
    @Getter @Setter private TestCase testCase;
}
