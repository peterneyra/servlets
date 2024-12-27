package org.pneira.apiservlet.webapp.headers.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Named;
import org.pneira.apiservlet.webapp.headers.configs.Service;
import org.pneira.apiservlet.webapp.headers.models.Categoria;
import org.pneira.apiservlet.webapp.headers.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Alternative
@Service
public class ProductoServiceImpl implements ProductoService{

    @Override
    public List<Producto> listProducto() {
        return Arrays.asList( new Producto(1L,"notebook","Computacion", 175000),
                new Producto(2L,"Mesa escritorio","Oficina", 100000),
                new Producto(3L,"Teclado mencanico","Computacion", 40000),
                new Producto(4L,"Silla de Oficina","Oficina", 80000),
                new Producto(5L,"Mouse Logitech","Computacion", 50000),
                new Producto(6L,"Lampara de Noche","Oficina", 40000)
                );
    }

    @Override
    public Optional<Producto> porIdBuscar(Long id) {
        return listProducto().stream().filter( i -> i.getId().equals(id)).findAny();
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<Categoria> listarCategoria() {
        return null;
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        return Optional.empty();
    }
}
