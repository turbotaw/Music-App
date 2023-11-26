package com.music.musicapp.util.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Adjust this to be more specific if needed
                        .allowedOrigins("http://localhost:3000") // Your frontend's URL
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Allowed HTTP methods
            }
        };
    }
}