package com.Flipr.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        
        registry.addMapping("/user/**")
                .allowedOrigins("http://localhost:3000","https://real-trust-flipr-production-eb44.up.railway.app")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);


        registry.addMapping("/admin/**")
                .allowedOrigins("http://localhost:3000","https://real-trust-flipr-production-eb44.up.railway.app")
                .allowedMethods("GET", "POST","DELETE","PUT", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);

    }
}

