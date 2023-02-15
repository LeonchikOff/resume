package org.example.resume.controllers;

import org.example.resume.services.NameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/search", loadOnStartup = 1)
public class SearchController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doGet search");
        req.getRequestDispatcher("/WEB-INF/jsp/search-form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.debug("doPost search: {}", req.getParameterMap());
        String query = req.getParameter("query");
        if(query == null || query.trim().isEmpty()) {
            req.setAttribute("invalid", Boolean.TRUE);
            req.getRequestDispatcher("/WEB-INF/jsp/search-form.jsp").forward(req, resp);
        } else {
            String name = NameService.getInstance().convertName(query);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/WEB-INF/jsp/search-result.jsp").forward(req, resp);
        }
    }
}
