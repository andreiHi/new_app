package com.app.configuration;

import com.app.repository.impl.ExtendedRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 22.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.app.repository", repositoryBaseClass = ExtendedRepositoryImpl.class)
public class ExtendedRepositoryConfiguration  {

}
