package com.dev.bank.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.dev.bank.controllers", "com.dev.bank.services"})
public class AppConfig {
}
