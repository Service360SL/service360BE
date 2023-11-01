package com.service360.group50.config;

import com.service360.group50.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService () {
        return username -> userRepository.findByEmailIgnoreCase ( username )
                .orElseThrow ( () -> new RuntimeException ( "Users not found" ) );
    }

    @Bean
    public AuthenticationProvider authenticationProvider (){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider ( );
        authProvider.setUserDetailsService ( userDetailsService ( ) );
        authProvider.setPasswordEncoder ( PasswordEncoder ( ) );

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config ) throws Exception {
        return config.getAuthenticationManager ();
    }

    @Bean
    public PasswordEncoder PasswordEncoder () {
        return new BCryptPasswordEncoder (  );
    }

}