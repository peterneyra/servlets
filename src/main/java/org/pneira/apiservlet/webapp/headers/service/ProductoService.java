package org.pneira.apiservlet.webapp.headers.service;

import org.pneira.apiservlet.webapp.headers.models.Categoria;
import org.pneira.apiservlet.webapp.headers.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {


    List<Producto> listProducto();

    Optional<Producto> porIdBuscar(Long id);

    void guardar(Producto producto);
    void eliminar(Long id);
    List<Categoria> listarCategoria();
    Optional<Categoria> porIdCategoria(Long id);



}
