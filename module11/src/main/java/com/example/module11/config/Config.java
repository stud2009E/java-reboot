package com.example.module11.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "com.example.module11.dao")
@Configuration
@PropertySource("classpath:application.yml")
public class Config {
}
