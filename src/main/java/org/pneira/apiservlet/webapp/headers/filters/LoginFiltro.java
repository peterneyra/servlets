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


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        LoginService loginService = new LoginServiceSessionImpl();
        Optional<String> username = loginService.getUsername((HttpServletRequest) req);
        if (username.isPresent()){

            filterChain.doFilter(req,resp);  //
        } else{
            ((HttpServletResponse)resp).sendError( HttpServletResponse.SC_UNAUTHORIZED, "LoginFiltro: lo sentimos no estas autorizado");
        }
    }
}
