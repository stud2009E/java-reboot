package ru.edu.module12.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "ru.edu.module12.dao")
@Configuration
@PropertySource("classpath:application.yml")
public class Config {
}
