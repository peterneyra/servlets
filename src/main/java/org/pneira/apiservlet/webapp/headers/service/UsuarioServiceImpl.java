package org.pneira.apiservlet.webapp.headers.service;

import jakarta.inject.Inject;
import org.pneira.apiservlet.webapp.headers.configs.Service;
import org.pneira.apiservlet.webapp.headers.models.Usuario;
import org.pneira.apiservlet.webapp.headers.repositories.UsuarioRepository;


import java.sql.SQLException;
import java.util.Optional;

@Service    // @ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService{

    @Inject         // tambien via variable o  abajo constructor comentado
    private UsuarioRepository userRepo ;

   // Connection conn ; ya no va
    // aqui injectamos via constructor el UsuarioRepositorio y  la conexion la pasamos en el respositorio
 /*   @Inject
    public UsuarioServiceImpl(UsuarioRepository ur ) {
        this.userRepo = ur;
    }*/

    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepo.porUsername(username)).filter( u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException( e.getMessage(), e.getCause());
        }
    }
}
