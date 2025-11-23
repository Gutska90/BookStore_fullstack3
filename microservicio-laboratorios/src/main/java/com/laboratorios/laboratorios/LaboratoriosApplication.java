package com.laboratorios.laboratorios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaboratoriosApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LaboratoriosApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🚀 Microservicio Laboratorios iniciado correctamente");
        System.out.println("📡 Puerto: 8082");
        System.out.println("🌐 API Base URL: http://localhost:8082/api/laboratorios");
        System.out.println("==============================================");
    }
}

