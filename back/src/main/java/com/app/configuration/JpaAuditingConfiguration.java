package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 23.05.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          */
//        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        System.out.println(name);
        return () -> Optional.ofNullable("chathuranga");
    }
}
