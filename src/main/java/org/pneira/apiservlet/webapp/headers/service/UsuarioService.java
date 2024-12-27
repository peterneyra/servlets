package org.pneira.apiservlet.webapp.headers.service;

import org.pneira.apiservlet.webapp.headers.models.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public interface UsuarioService {

    Optional<Usuario> login(String username, String password) ;
}
