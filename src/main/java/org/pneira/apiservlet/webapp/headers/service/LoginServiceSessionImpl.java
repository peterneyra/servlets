package org.pneira.apiservlet.webapp.headers.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

@ApplicationScoped
public class LoginServiceSessionImpl implements LoginService {
    @Override
    public Optional<String> getUsername(HttpServletRequest request) {

        HttpSession httpSession = request.getSession();         // en lugar de getCookie
        String user = (String) httpSession.getAttribute("username");

       /* if ( user != null){
            return Optional.of(user); } */

        return (user != null) ? Optional.of(user) : Optional.empty();
    }
}
