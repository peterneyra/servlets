package org.pneira.apiservlet.webapp.headers.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.pneira.apiservlet.webapp.headers.service.LoginService;
import org.pneira.apiservlet.webapp.headers.service.LoginServiceCookieImpl;
import org.pneira.apiservlet.webapp.headers.service.LoginServiceSessionImpl;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Inject
    private LoginService ls;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //LoginService ls = new LoginServiceSessionImpl();   // <--------- session
        Optional<String> username = ls.getUsername(req);
        if (username.isPresent()){
            HttpSession session = req.getSession();
            session.invalidate();  // borra si hubiera carrito de compras y todo lo guardado en la session
            /**  Cookie userNameCookie = new Cookie("username","");
                 userNameCookie.setMaxAge(0);
                  resp.addCookie(userNameCookie);*/
        }
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
