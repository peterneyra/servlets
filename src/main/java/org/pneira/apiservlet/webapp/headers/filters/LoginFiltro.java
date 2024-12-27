package org.pneira.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.service.LoginService;
import org.pneira.apiservlet.webapp.headers.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

//Todo: aqui podemos agregar todas las rutas que queremos filtrar
@WebFilter({"/ver-carro","/agregar-carro"})   //Todo: actualizar prod. esta pendiente de mi parte hacerlo
public class LoginFiltro implements Filter {

    /** Se crea una sola vez en el ciclo de vida de la App como los Servlets*/
    /** Es por cada hilo o peticion que se conecta a nuestra App, realizar una tarea antes o despues del metodo del Servlet
     * guardar datos en DB, en Log siempre orientado a una peticion o request cuando inicializa o se destruye
     * agregar la anotacion @WebFilter que servlet se va aplicar, para validar aquellas paginas que son privadas
     * */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) req);
        if (username.isPresent()){
            // se va llamando de a uno , puede haber muchos filtros, ahora invocamos la cadena
            filterChain.doFilter(req,resp);  //
        } else{  // en caso que no esta autentificado
            ((HttpServletResponse)resp).sendError( HttpServletResponse.SC_UNAUTHORIZED, "LoginFiltro: lo sentimos no estas autorizado");
        }
    }
}
