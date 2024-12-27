package org.pneira.apiservlet.webapp.headers.interceptor;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.util.logging.Logger;

@Logging
@Interceptor
public class LoggingInterceptor {

    /** todo: podemos hacer algo antes manejo de transacciones, interceptar algun meetodos del service,
     * registrar algun metodol del LOG */
    @Inject
    private Logger log;
    @AroundInvoke
    public Object logging(InvocationContext ic) throws Exception {
        log.info( " ***** entrando antes de invocar el metodo"   + ic.getMethod().getName()
                + "de la clase: " + ic.getMethod().getDeclaringClass()   )  ;
        Object res = ic.proceed();  // esto es la invocacion del metodo, podemos hacer algo antes o despues

        log.info( "  ****** saliendo de la invocacion del metodo"   + ic.getMethod().getName());
        return res;
    }
}
