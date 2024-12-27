package org.pneira.apiservlet.webapp.headers.repositories;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.pneira.apiservlet.webapp.headers.configs.MysqlConn;
import org.pneira.apiservlet.webapp.headers.configs.RepositoryQ;
import org.pneira.apiservlet.webapp.headers.models.Categoria;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@RepositoryQ   // <-- reemplazado @ApplicationScoped
public class CategoriaRepositoryImpl implements Repository<Categoria>{


    private Connection conn;  // aqui se maneja CDI por constructor

    @Inject
    @Named("conn")
    public CategoriaRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> listCate = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from categorias ") ){
            while ( rs.next()){
                Categoria c = getCategoria(rs);
                listCate.add( c );
            }
        }
        return listCate;
    }

    @Override
    public Categoria porId(Long id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT from categorias  WHERE id = ?")){
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }


    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria p = new Categoria(
                rs.getLong("id" ),
                rs.getString( "nombre"));
        return p;
    }
}
