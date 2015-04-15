/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans.util;

import br.com.bob.dashboard.model.history.ClientReviewHistory;
import br.com.bob.dashboard.model.history.CodeCoverageHistory;
import br.com.bob.dashboard.model.history.CodeReviewHistory;
import br.com.bob.dashboard.model.history.EnterpriseArchitectHistory;
import br.com.bob.dashboard.model.history.ProductsHistory;
import br.com.bob.dashboard.model.history.SonarHistory;
import br.com.bob.dashboard.model.history.TestCaseHistory;
import br.com.bob.dashboard.model.history.TimeboxHistory;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
public class MetricTableHistory {
    
    @Getter @Setter private SonarHistory sonar;
    @Getter @Setter private CodeCoverageHistory coverage;
    @Getter @Setter private ProductsHistory product;
    @Getter @Setter private EnterpriseArchitectHistory ea;
    @Getter @Setter private CodeReviewHistory codeReview;
    @Getter @Setter private ClientReviewHistory clientReview;
    @Getter @Setter private TimeboxHistory timebox;
    @Getter @Setter private TestCaseHistory testCase;

    public MetricTableHistory() {}

    public MetricTableHistory(SonarHistory sonar, CodeCoverageHistory coverage, 
            ProductsHistory product, EnterpriseArchitectHistory ea, CodeReviewHistory codeReview, 
            ClientReviewHistory clientReview, TimeboxHistory timebox, TestCaseHistory testCase) {
        this.sonar = sonar;
        this.coverage = coverage;
        this.product = product;
        this.ea = ea;
        this.codeReview = codeReview;
        this.clientReview = clientReview;
        this.timebox = timebox;
        this.testCase = testCase;
    }
}
