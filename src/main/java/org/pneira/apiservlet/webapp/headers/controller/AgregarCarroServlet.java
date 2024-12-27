package org.pneira.apiservlet.webapp.headers.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.pneira.apiservlet.webapp.headers.configs.ProductosServicesPrincipal;
import org.pneira.apiservlet.webapp.headers.models.Carro;
import org.pneira.apiservlet.webapp.headers.models.ItemCarro;
import org.pneira.apiservlet.webapp.headers.models.Producto;
import org.pneira.apiservlet.webapp.headers.service.ProductoService;


import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Inject   //   @Named("defecto")
    @ProductosServicesPrincipal
    private ProductoService prodService;
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        /*Connection conn =  (Connection)req.getAttribute("conn");
        ProductoService prodService = new ProductoServiceJdbcImpl(conn);*/

        Optional<Producto> producto=  prodService.porIdBuscar( id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get() );
       // ya no es necesario , por que CDI lo hace
       //     HttpSession session = req.getSession();
       //     Carro carro = (Carro) session.getAttribute("carro");
            //if (session.getAttribute("carro") == null ){
                /*    llevado al Listener al crear sessionCreated(), entonces siempre se crea al iniciar la session
                 *                  carro = new Carro();
                 *                 session.setAttribute("carro",carro);                 */
           // }else{
                //carro = (Carro) session.getAttribute("carro");  subido arriba
           // }
            carro.addItemCarro(item);
        }
        resp.sendRedirect( req.getContextPath() + "/ver-carro");

    }
}
