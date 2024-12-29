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

@SuppressWarnings("unused")
@WebServlet("/agregar-carro")
public class AgregarCarroServlet extends HttpServlet {

    @Inject
    @ProductosServicesPrincipal
    private ProductoService prodService;
    @Inject
    private Carro carro;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        Optional<Producto> producto=  prodService.porIdBuscar( id);
        if (producto.isPresent()){
            ItemCarro item = new ItemCarro(1,producto.get() );
            carro.addItemCarro(item);
        }
        resp.sendRedirect( req.getContextPath() + "/ver-carro");

    }
}
