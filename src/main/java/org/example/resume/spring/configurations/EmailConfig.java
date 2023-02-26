package org.example.resume.spring.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(configurableEnvironment.getRequiredProperty("email.smtp.server"));
        if (configurableEnvironment.containsProperty("email.smtp.username")) {
            javaMailSender.setUsername(configurableEnvironment.getRequiredProperty("email.smtp.username"));
            javaMailSender.setPassword(configurableEnvironment.getRequiredProperty("email.smtp.password"));
            javaMailSender.setPort(Integer.parseInt(configurableEnvironment.getRequiredProperty("email.smtp.port")));
            javaMailSender.setDefaultEncoding("UTF-8");
            Properties javaMailProperties = new Properties();
            javaMailProperties.setProperty("mail.smtp.auth", "true");
            javaMailProperties.setProperty("mail.smtp.starttls.enable", "true");
            javaMailSender.setJavaMailProperties(javaMailProperties);
        }
        return javaMailSender;
    }
}
