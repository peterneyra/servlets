package org.pneira.apiservlet.webapp.headers.repositories;


import jakarta.inject.Inject;

import org.pneira.apiservlet.webapp.headers.configs.MysqlConn;
import org.pneira.apiservlet.webapp.headers.configs.RepositoryQ;
import org.pneira.apiservlet.webapp.headers.models.Usuario;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RepositoryQ //@ApplicationScoped
public class UsuarioRepositoryImpl implements UsuarioRepository{

    @Inject
    @MysqlConn //    @Named("conn")
    private Connection conn;


    @Override
    public List<Usuario> listar() throws SQLException {
        return null;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Usuario usuario) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    @Override
    public Usuario porUsername(String username) throws SQLException {
        Usuario user = null;
        try(PreparedStatement stmt = conn.prepareStatement( "SELECT * from usuarios where username =? " )){
            stmt.setString( 1, username );
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    user=new Usuario();
                    user.setId( rs.getLong("id") );
                    user.setUsername( rs.getString("username") );
                    user.setPassword( rs.getString("password") );
                    user.setEmail( rs.getString("email") );
                }
            }
        }
        return user;
    }
}
