package org.example.resume.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);

    @Value("${application.production}")
    private boolean production;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("production", production);
        LOGGER.info("Application Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Application Context Destroyed");
    }
}
