package com.inova.bpm.mediator.jbpmmediator.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExceptionHandler extends Exception {

    private static final Log LOGGER = LogFactory.getLog(ExceptionHandler.class);

    public ExceptionHandler(String message) {

        super(message);

        LOGGER.info("Successfully created ExceptionHandler.");
    }
}
