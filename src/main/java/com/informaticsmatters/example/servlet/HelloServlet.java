package com.informaticsmatters.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

@WebServlet(value = "/hello", name = "HelloServlet", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(HelloServlet.class.getName());
    private String greeting;

    @Override
    public void init() throws ServletException {
        super.init();
        greeting = System.getenv("GREETING");
        if (greeting == null) {
            greeting = "Hello";
        }
        LOG.warning("Servlet initialized at /hello. Greeting is " + greeting);
    }


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        LOG.warning("Received GET");

        Principal principal = req.getUserPrincipal();
        String username = (principal == null ? "unauthenticated user" : principal.getName());
        LOG.warning("Username is " + username);
        res.getWriter().println(greeting + " " + username);
    }

}
