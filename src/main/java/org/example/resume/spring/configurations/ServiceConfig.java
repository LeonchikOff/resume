package org.example.resume.spring.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = {
        "org.example.resume.services",
        "org.example.resume.filters",
        "org.example.resume.listeners",
        "org.example.resume.components"})
@PropertySource("classpath:logic.properties")
public class ServiceConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer
                .setLocations(
                        new ClassPathResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }
}
