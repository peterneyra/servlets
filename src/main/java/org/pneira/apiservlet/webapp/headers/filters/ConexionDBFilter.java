package org.pneira.apiservlet.webapp.headers.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.service.ServiceJdbcException;

import java.io.IOException;

@SuppressWarnings("unused")
@WebFilter("/*")
public class ConexionDBFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {

            try {

                filterChain.doFilter(req, resp);
            } catch ( ServiceJdbcException e) {

                ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());

            }

    }
}
