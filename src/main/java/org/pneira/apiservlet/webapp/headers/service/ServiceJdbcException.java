package org.pneira.apiservlet.webapp.headers.service;

public class ServiceJdbcException extends  RuntimeException{

    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
