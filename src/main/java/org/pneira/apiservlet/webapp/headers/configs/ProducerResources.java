package org.pneira.apiservlet.webapp.headers.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

@Dependent  // mp depende de nadie o @ApplicationScope
public class ProducerResources {
    /** una conexion a la BD es un recurso */

    //@Inject
   // private Logger log;

    @Resource(name="jdbc/mysqlDB")
    private DataSource ds;
    @Produces
    @RequestScoped
    @MysqlConn    //    @Named("conn")   reemplaza
    private Connection beanConnection() throws NamingException, SQLException {
     /*  Context initContext = new InitialContext();
         Context envContext = (Context) initContext.lookup("java:/comp/env");
         DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");         */
        return ds.getConnection();
    }

    @Produces //     para que se registre en el contenedor de CDI, y va tomar del contexto donde se indique
    private Logger beanLogger(InjectionPoint iPt){  //nos da informacion METADATA de la clase
        return Logger.getLogger(iPt.getMember().getDeclaringClass().getName());
    }

    // forma de cerrar la conexion, tambien por filtro que se retiro del ( ) en el try es valido
    public void close(@Disposes @MysqlConn Connection connection) throws SQLException {
        connection.close();
       System.out.println("cerrando la conexion de la BD mysql");
       // log.info( "cerrando la conexion de la BD mysql");
    }
}
