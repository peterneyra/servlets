package org.pneira.apiservlet.webapp.headers.controller;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.pneira.apiservlet.webapp.headers.models.Usuario;
import org.pneira.apiservlet.webapp.headers.service.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {

    @Inject
    private LoginService lService;

    @Inject
    private UsuarioService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> usernameOptional = lService.getUsername(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html ");
                out.println("<head>");
                out.println("    <meta charset=\"UTF-8\">");
                out.println("    <title>CCOOKIE " + usernameOptional + " </title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola \"" + usernameOptional.get() + "\"  ... has iniciado session con exito" + "</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.jsp' > - Volver </a> </p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout' > Cerrar Sesión </a> </p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            req.setAttribute("title",req.getAttribute("title") + " : Login ");
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Optional<Usuario> userOpt = userService.login( username, password);


        if ( userOpt.isPresent()) {

            req.getSession().setAttribute("username", username);


            resp.sendRedirect(req.getContextPath() + "/login");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "El user o password no coincide");
        }
    }
}
