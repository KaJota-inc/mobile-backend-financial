package com.example.springwithPostgreSql.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry corsRegistry){
                corsRegistry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedHeaders("GET")
                        .exposedHeaders("Authorization")
                        .allowCredentials(true);
            }
        };
    }
}
