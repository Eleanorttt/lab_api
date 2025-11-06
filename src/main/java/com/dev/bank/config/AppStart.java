package com.dev.bank.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}

// UI(User Interface) ------- JS/TS, HTML. css, Android, iOS -------------- NO LOGIC, ONLY DISPLAY INFORMATION
// -------------------------- Communication - (View) - JSON/XML -----------
// REST API ----------------- Java, Spring, Hibernate, ORM ---------------- LOGIC
// Database ----------------- Mysql 8 ------------------------------------- STORE DATA


// config - files with configurations
// controllers - classes with endpoints (view of rest api)
// services - logic
// model - classes with structure of data models
// dao - data access objects
// repo - helpers with access to DB


// ARCHITECTURE

// Authentication
// Users
// Account
// Security --- custom token security
// Actions