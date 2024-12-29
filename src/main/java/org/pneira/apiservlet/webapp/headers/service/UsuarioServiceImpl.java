package org.pneira.apiservlet.webapp.headers.service;

import jakarta.inject.Inject;
import org.pneira.apiservlet.webapp.headers.configs.Service;
import org.pneira.apiservlet.webapp.headers.models.Usuario;
import org.pneira.apiservlet.webapp.headers.repositories.UsuarioRepository;


import java.sql.SQLException;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Inject
    private UsuarioRepository userRepo ;



    @Override
    public Optional<Usuario> login(String username, String password) {
        try {
            return Optional.ofNullable(userRepo.porUsername(username)).filter( u -> u.getPassword().equals(password));
        } catch (SQLException e) {
            throw new ServiceJdbcException( e.getMessage(), e.getCause());
        }
    }
}
