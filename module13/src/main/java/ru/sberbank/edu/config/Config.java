package ru.sberbank.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "ru.sberbank.edu.dao")
@Configuration
public class Config {
}
