package org.pneira.apiservlet.webapp.headers.configs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Stereotype;
import jakarta.inject.Named;
import org.pneira.apiservlet.webapp.headers.interceptor.Logging;
import org.pneira.apiservlet.webapp.headers.interceptor.TransactionalJDBC;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@TransactionalJDBC
@Logging
@ApplicationScoped
@Stereotype
@Named
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
