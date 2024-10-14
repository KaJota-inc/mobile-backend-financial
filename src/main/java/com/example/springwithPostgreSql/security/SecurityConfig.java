package com.example.springwithPostgreSql.security;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @SneakyThrows
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .authorizeHttpRequests(
                        auth->auth.anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
