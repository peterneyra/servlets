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
        /**  ejem para guardar un objeto singleton global para toda la aplicacion y compartido por todo*/
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
        // aqui se peude pasa la conexion a la bd
        // tiene el alcanze del request despues se destruye, por ejemplo El carrtio de compras
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
        /**   SESSION INICIA
         *  cuando se crea session JSESSIONID, podemos inicializar alguna variable, objeto , carrito de compra etc...  */
        serveltContext.log(" sessionCreated 5 ");
    /*  ya no es necesario crearlo manualmente ahora esta CDI
        Carro carro = new Carro();
        HttpSession session = se.getSession();  // capturamos la sesion y guardar objetos
        session.setAttribute("carro",carro);*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        serveltContext.log(" session Destroyed 6 ");
    }
}
