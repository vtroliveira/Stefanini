/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bob.dashboard.web.context.handler;

import javax.validation.ValidationException;

/**
 *
 * @author Vitor
 */
public class ValidatorExceptionHandler extends ContextExceptionHandler {

    @Override
    protected Class<ValidationException> type() { return ValidationException.class; }

    @Override
    protected boolean showStackTrace() { return false; }
}