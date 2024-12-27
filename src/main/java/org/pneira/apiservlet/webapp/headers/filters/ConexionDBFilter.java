package org.pneira.apiservlet.webapp.headers.filters;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.configs.MysqlConn;
import org.pneira.apiservlet.webapp.headers.service.ServiceJdbcException;
import org.pneira.apiservlet.webapp.headers.util.ConexionBaseDatosDS;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionDBFilter implements Filter {

    /* @Inject   es retirado por haber creado el interceptos transactionalInterceptor
    @MysqlConn //     @Named("conn")
    private Connection conn;    */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        /*try  {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }*/
            try { //Todo: hasta aqui manejamos conexion por transaccion. guardamos en el request y pasamos a los servlets
               // req.setAttribute("conn", connRequest);// ya no seria necesario guardarlo en el request, se injecta desde el repositorio
                filterChain.doFilter(req, resp);
              //  connRequest.commit();
            } catch ( ServiceJdbcException e) {
             //   connRequest.rollback();
                ((HttpServletResponse) resp).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
     /*   } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
