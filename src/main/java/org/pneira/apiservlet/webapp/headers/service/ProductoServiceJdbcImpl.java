package org.pneira.apiservlet.webapp.headers.service;


import jakarta.inject.Inject;
import org.pneira.apiservlet.webapp.headers.configs.ProductosServicesPrincipal;
import org.pneira.apiservlet.webapp.headers.configs.Service;
import org.pneira.apiservlet.webapp.headers.models.Categoria;
import org.pneira.apiservlet.webapp.headers.models.Producto;
import org.pneira.apiservlet.webapp.headers.repositories.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



@Service
@ProductosServicesPrincipal
public class ProductoServiceJdbcImpl implements ProductoService{




@SuppressWarnings("unused")
    @Inject
    private Repository<Producto> repositoryProdJdbc;
    @Inject
    private Repository<Categoria> repositoryCategoriaJdbc;


    @Override
    public List<Producto> listProducto() {
        List<Producto> list ;
        try {
          list   = repositoryProdJdbc.listar();
        } catch (SQLException e) {
           throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return list;
    }

    @Override
    public Optional<Producto> porIdBuscar(Long id) {
        try {
            return Optional.ofNullable( repositoryProdJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Producto producto) {
        try {
            repositoryProdJdbc.guardar(producto);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void eliminar(Long id) {
        try {
            repositoryProdJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return repositoryCategoriaJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Long id) {
        try {
            return Optional.ofNullable( repositoryCategoriaJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
