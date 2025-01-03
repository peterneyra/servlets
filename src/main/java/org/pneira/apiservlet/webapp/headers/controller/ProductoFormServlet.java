package org.pneira.apiservlet.webapp.headers.controller;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.configs.ProductosServicesPrincipal;
import org.pneira.apiservlet.webapp.headers.models.Categoria;
import org.pneira.apiservlet.webapp.headers.models.Producto;
import org.pneira.apiservlet.webapp.headers.service.ProductoService;
import org.pneira.apiservlet.webapp.headers.service.ProductoServiceJdbcImpl;
import org.pneira.apiservlet.webapp.headers.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@WebServlet("/productos/form")
public class ProductoFormServlet extends HttpServlet {


    @Inject
    @ProductosServicesPrincipal
    private ProductoService serviceProd;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        }catch (NumberFormatException e){
            id=0L;
        }
        Producto producto = new Producto();
        producto.setCategoria( new Categoria());
        if ( id > 0){
            Optional<Producto>  o = serviceProd.porIdBuscar(id);
            if ( o.isPresent()){
                producto = o.get();
            }
        }
        req.setAttribute("title",req.getAttribute("title") + " : Formulario Productos");
        req.setAttribute("categorias", serviceProd.listarCategoria());
        req.setAttribute("producto",producto);
        getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String nombre = req.getParameter("nombre");
            Integer precio;
            try {
                precio = Integer.valueOf(req.getParameter("precio"));
            } catch (NumberFormatException e) {
                precio = 0;
            }
            String sku = req.getParameter("sku");
            String fechaStr = req.getParameter("fecha_registro");
            Long categoriaId;
            try {
                categoriaId = Long.valueOf(req.getParameter("categoria"));
            } catch (NumberFormatException e) {
                categoriaId = 0L;
            }

            Map<String, String> errores = new HashMap<>();
            if (nombre == null || nombre.isBlank()) {
                errores.put("nombre", "El nombre es requerido");
            }
            if (sku == null || sku.isBlank()) {
                errores.put("sku", "El sku es requerido");
            }
            if (fechaStr == null || fechaStr.isBlank()) {
                errores.put("fecha_registro", "la fecha es requerido");
            }
            if (precio.equals(0)) {
                errores.put("precio", "El precio es requerido");
            }
            if (categoriaId.equals(0L)) {
                errores.put("categoria", "La Categoria es requerido");
            }

            LocalDate fecha;
            try {
                fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                fecha = null;
            }
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException e) {
                id = 0L;
            }
            Producto producto = new Producto(nombre, new Categoria(categoriaId), precio, sku, fecha);
            producto.setId(id);

            if (errores.isEmpty()) {
                serviceProd.guardar(producto);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                req.setAttribute("errores", errores);

                req.setAttribute("categorias", serviceProd.listarCategoria());
                req.setAttribute("producto", producto);
                req.setAttribute("title", req.getAttribute("title") + " : Formulario de Productos");
                getServletContext().getRequestDispatcher("/form.jsp").forward(req, resp);
            }



    }
}
