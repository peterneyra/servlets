package org.pneira.apiservlet.webapp.headers.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.configs.ProductosServicesPrincipal;
import org.pneira.apiservlet.webapp.headers.models.Producto;
import org.pneira.apiservlet.webapp.headers.service.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@WebServlet({"/productos", "/productos.html"})
public class ProductoXlsHTMLServlet extends HttpServlet {

    @Inject //    @Named("defecto")
    @ProductosServicesPrincipal  // solo movemos esto en la nueva clase
    private ProductoService prodService;

    @Inject
    private LoginService lservice;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Producto> prod = prodService.listProducto();

        Optional<String> usernameOptional = lservice.getUsername(req);

        req.setAttribute("title",req.getAttribute("title") + " : Listado de Productos");
        req.setAttribute("productos",prod);
        req.setAttribute("username", usernameOptional.orElse(""));

        req.setAttribute( "error" ," mostando valor");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Connection conn = (Connection) req.getAttribute("conn");
        ProductoService prodService= new ProductoServiceJdbcImpl(conn);*/

        String cadena =  (String) req.getParameter("cadena");

        if (cadena == null){

            getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
        }
        if ( cadena.equals("si")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0L;
            }
            prodService.eliminar(id);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        }
    }
}
