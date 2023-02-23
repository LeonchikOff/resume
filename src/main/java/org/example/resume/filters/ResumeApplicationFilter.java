package org.example.resume.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class ResumeApplicationFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(ResumeApplicationFilter.class);

    @Value("${application.production}")
    private boolean production;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();
        req.setAttribute("REQUEST_URI", requestURI);
        try {
            LOGGER.info("\n******************************** Before URL processing: {} *****************", req.getRequestURI());
            chain.doFilter(req, resp);
            LOGGER.info("\n******************************** After  URL processing: {} *****************\n\n", req.getRequestURI());
        } catch (Throwable throwable) {
            LOGGER.error("Process request failed: " + requestURI, throwable);
            if (!production) {
                throw new ServletException(throwable);
            } else {
                if (requestURI.equals("/error") || requestURI.startsWith("/fragment")) {
                    resp.reset();
                    resp.getWriter().write("");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                } else
                    resp.sendRedirect("/error?url=" + requestURI);
            }
        }
    }
}
