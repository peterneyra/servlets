package org.pneira.apiservlet.webapp.headers.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

public interface LoginService {

    Optional<String> getUsername(HttpServletRequest request);


}
