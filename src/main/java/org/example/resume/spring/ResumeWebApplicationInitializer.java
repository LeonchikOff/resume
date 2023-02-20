package org.example.resume.spring;

import org.example.resume.filters.ApplicationFilter;
import org.example.resume.listeners.ApplicationListener;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import java.util.EnumSet;

public class ResumeWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContainer) throws ServletException {
        servletContainer.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));

        /* Scanning via the AnnotationConfigWebApplicationContext  of classes containing beans configurations
           marked with the @Configuration annotation along the specified paths  */
        AnnotationConfigWebApplicationContext springCdiWebApplicationContainer = new AnnotationConfigWebApplicationContext();
        springCdiWebApplicationContainer.scan("org.example.resume.spring.configurations");
        springCdiWebApplicationContainer.setServletContext(servletContainer);
        springCdiWebApplicationContainer.refresh();

        // Initializing the front controller SpringMVCDispatcherServlet into javax.servlet.ServletContext
        DispatcherServlet dispatcherServlet = new DispatcherServlet(springCdiWebApplicationContainer);
        ServletRegistration.Dynamic servletRegistration =
                servletContainer.addServlet("dispatcherServlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");

        //registrations listeners
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(springCdiWebApplicationContainer);
        servletContainer.addListener(contextLoaderListener);
        ApplicationListener applicationListener = springCdiWebApplicationContainer.getBean(ApplicationListener.class);
        servletContainer.addListener(applicationListener);

        //registrations filters
        ApplicationFilter applicationFilter = springCdiWebApplicationContainer.getBean(ApplicationFilter.class);
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter("UTF-8", true);
        ConfigurableSiteMeshFilter siteMeshFilter = new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder.addDecoratorPath("/*", "/WEB-INF/templates/page-template.jsp");
            }
        };

        servletContainer
                .addFilter(applicationFilter.getClass().getSimpleName(), applicationFilter)
                .addMappingForUrlPatterns(null, true, "/*");
        servletContainer
                .addFilter(encodingFilter.getClass().getSimpleName(), encodingFilter)
                .addMappingForUrlPatterns(null, true, "/*");
        servletContainer
                .addFilter("siteMeshFilter", siteMeshFilter)
                .addMappingForUrlPatterns(null, true, "/*");
    }
}

