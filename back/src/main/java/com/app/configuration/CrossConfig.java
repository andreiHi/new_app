package com.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Configuration
public class CrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println(">=== Inside Cors Orgin Mapping addCorsMappings ===>");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET",  "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
