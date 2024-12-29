package org.pneira.apiservlet.webapp.headers.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.pneira.apiservlet.webapp.headers.models.Carro;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    ServletContext serveltContext ;
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().log("Inicializando la Aplicación  1");
        serveltContext = sce.getServletContext();
        serveltContext.setAttribute("mensaje","Algun Valor global en la Aplicacion!!!! :P ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Destruyendo Context global 2");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        sre.getServletContext().log("request inizializado 3");
        sre.getServletRequest().setAttribute("mensaje","guardando un valor para el request! ");
        sre.getServletRequest().setAttribute("title","Catalogo Servlet "); // x default
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log( "Request  Destruido 4");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        serveltContext.log(" sessionCreated 5 ");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        serveltContext.log(" session Destroyed 6 ");
    }
}
