package org.example.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "controller", value = "/controller")
public class ControllerServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ControllerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "Received GET request for {0}", request.getRequestURI());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usermanager");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "Received POST request for {0}", request.getRequestURI());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Usermanager");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "Received DELETE request for {0}", request.getRequestURI());
        String requestType = request.getHeader("X-Request-Type");
    }
}
