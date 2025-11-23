package com.laboratorios.resultados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResultadosApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ResultadosApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🚀 Microservicio Resultados iniciado correctamente");
        System.out.println("📡 Puerto: 8083");
        System.out.println("🌐 API Base URL: http://localhost:8083/api/resultados");
        System.out.println("==============================================");
    }
}

