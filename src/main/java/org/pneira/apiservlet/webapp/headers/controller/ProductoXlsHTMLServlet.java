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

        //Connection conn =  (Connection)req.getAttribute("conn");  // para usar DB
        //ProductoService prodService = new ProductoServiceJdbcImpl(conn); // todo new ya no va, se inyecta

        List<Producto> prod = prodService.listProducto();

        // _A_ LoginService lservice = new LoginServiceSessionImpl();      // <------------------ session  // se elimina x CDI
        Optional<String> usernameOptional = lservice.getUsername(req);



        /**  FUE LLEVADO A listar.JSP  y viene el siguiente codigo : */
        req.setAttribute("title",req.getAttribute("title") + " : Listado de Productos");
        req.setAttribute("productos",prod);
        req.setAttribute("username", usernameOptional.orElse(""));

        req.setAttribute( "error" ," mostando valor");
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);

        /** Capturamos de la session el carrito, esto puede ser en toda la App */
/*        String mensajeRequest = (String) req.getAttribute("mensaje");  // viene por cada request (Se crea y destruye c/Request)
        String mensajeGlobal = (String )getServletContext().getAttribute("mensaje");  // viene de un Singleton
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html ");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Listado de productos probando cookies</title>");
            out.println("<br>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Productos probando cookies</h1>");
            if (usernameOptional.isPresent()) {
                out.println("<div><h1 style= 'color: blue'> Hola ... " + usernameOptional.get() + " Bienvenido al Sistema</h1> </div>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> id </th>");
            out.println("<th> nombre </th>");
            out.println("<th> tipo </th>");
            if (usernameOptional.isPresent()) {
                out.println("<th> precio </th>");
                out.println("<th> Agregar </th>");
            }
            out.println("</tr>");
            prod.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getTipo() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                    out.println("<td><a href =\""+ req.getContextPath() +"/agregar-carro?id="+p.getId()+"\"> Agregar al carrito  </a></td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("<p>Mensaje de la App " + mensajeGlobal +" </p>");
            out.println("<p>Mensaje por cada Request " + mensajeRequest +" </p>");
            out.println("</body>");
            out.println("</html>");
        }*/



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* Connection conn = (Connection) req.getAttribute("conn");
        ProductoService prodService= new ProductoServiceJdbcImpl(conn);*/

        String cadena =  (String) req.getParameter("cadena");
        System.out.println("  ----------- "  +cadena );
        if (cadena == null){
           // req.setAttribute("error", "valor cadena es null");
            getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
        }
        if ( cadena.equals("si")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0L;    // ?????????????????????????? corregir luego
            }
            prodService.eliminar(id);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
           // resp.sendRedirect(req.getContextPath() + "/productos");
        }
    }
}
