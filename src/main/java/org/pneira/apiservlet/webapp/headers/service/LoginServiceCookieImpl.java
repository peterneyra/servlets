package org.pneira.apiservlet.webapp.headers.service;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Optional;

//@ApplicationScoped
//@Alternative
public class LoginServiceCookieImpl implements LoginService{
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
        Cookie[] arrayCookies = req.getCookies() != null ? req.getCookies(): new Cookie[0] ;
        //Arrays.asList(arryCookies).stream().filter( a,b -> a.getValue().contains("username"));

        return Arrays.stream( arrayCookies).filter(a -> "username".equals(a.getName()))
                .map( Cookie::getValue).findAny();
    }
}
