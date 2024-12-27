package org.pneira.apiservlet.webapp.headers.configs;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Qualifier  //1.-  anotacion de tipo calificador, sera inyectado con este nombre MYsql
@Retention(RetentionPolicy.RUNTIME)   //aplicara la @ en tiempo de ejecucion.  ->import static y podremos usar toodo
@Target({METHOD, FIELD, PARAMETER, TYPE, CONSTRUCTOR }) // donde aplicar? en :  metodo atributo o parametro o confstructor
public @interface MysqlConn {
}
