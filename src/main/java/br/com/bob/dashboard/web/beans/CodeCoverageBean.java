/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.beans;

import br.com.bob.dashboard.model.CodeCoverage;
import br.com.bob.dashboard.web.beans.util.MetricBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@Named @RequestScoped
public class CodeCoverageBean extends MetricBean<CodeCoverage> {}