/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.service;

import br.com.bob.dashboard.exception.BusinessException;
import br.com.bob.dashboard.model.TimeboxDef;
import br.com.bob.dashboard.model.repository.TimeboxDAO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Vitor <vtr.oliveira at Nerv>
 */
@RequestScoped
public class TimeboxService {
    @Inject private TimeboxDAO dao;
    
    public TimeboxDef create(final TimeboxDef tb) {
        final TimeboxDef found = dao.find(tb.getNumber(), tb.getPhase());
        if (found != null)
            return found;
        else {
            try {
                return dao.save(tb);
            } catch (Exception e) {
                throw new BusinessException("Erro ao criar timebox", e);
            }
        }
    }
}
