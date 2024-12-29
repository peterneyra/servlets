package org.pneira.apiservlet.webapp.headers.interceptor;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.pneira.apiservlet.webapp.headers.configs.MysqlConn;
import org.pneira.apiservlet.webapp.headers.service.ServiceJdbcException;

import java.sql.Connection;
import java.util.Objects;
import java.util.logging.Logger;

@TransactionalJDBC
@Interceptor
public class TransactionalInterceptor {

    @Inject
    @MysqlConn
    private Connection conn;

    @Inject
    private Logger log;

    @AroundInvoke
    public Object transactional(InvocationContext ic) throws Exception {


        if ( conn.getAutoCommit()){
            conn.setAutoCommit(false);
        }
        try {
            log.info( " ------> Iniciando transaccion " + ic.getMethod().getName() + " de clase>>: " + ic.getMethod().getDeclaringClass());
            Object res = ic.proceed();
            conn.commit();
            log.info( " ------> commit; y FIN de TRX " + ic.getMethod().getName() + " de clase>>: " + ic.getMethod().getDeclaringClass());
            return res;
        }catch (ServiceJdbcException e){
            conn.rollback();
            throw e;
        }
    }
}
