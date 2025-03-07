package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        System.err.println("La aplicación ha arrancado");
    }
}
