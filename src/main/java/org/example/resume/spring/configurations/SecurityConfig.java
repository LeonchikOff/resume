package org.example.resume.spring.configurations;

import org.example.resume.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

//extends AuthenticationConfiguration

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //a provider
        provider.setUserDetailsService(userDetailsService); //user details service
        provider.setPasswordEncoder(passwordEncoder()); //you can add password encoders too
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests()
                .requestMatchers("/my-profile", "/edit", "/edit/**", "/remove").hasAuthority(Constants.USER)
                .anyRequest().permitAll();
        http.formLogin()
                .loginPage("/sign-in")
                .loginProcessingUrl("/sign-in-handler")
                .usernameParameter("uid")
                .passwordParameter("password")
                .defaultSuccessUrl("/my-profile")
                .failureUrl("/sign-in-failed");
        http.logout()
                .logoutUrl("/sign-out")
                .logoutSuccessUrl("/welcome")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
        http.rememberMe()
                .rememberMeParameter("remember-me")
                .key("resume-online")
                .tokenRepository(persistenceTokenRepository());
        http.csrf()
                .disable();
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistenceTokenRepository() {
        JdbcTokenRepositoryImpl persistenceTokenRepository = new JdbcTokenRepositoryImpl();
        persistenceTokenRepository.setDataSource(dataSource);
        return persistenceTokenRepository;
    }


    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
////        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
}
