package com.laboratorio.microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de la aplicación Spring Boot
 * Sistema de Gestión de Laboratorios y Resultados de Análisis
 *
 * Microservicios implementados:
 * - Control de Usuarios (CRUD + Login con roles)
 * - Gestión de Resultados de Análisis (almacenamiento y consulta)
 */
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class LaboratorioMicroservicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratorioMicroservicioApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🏥 Sistema de Gestión de Laboratorios iniciado");
        System.out.println("📡 Puerto: 8080");
        System.out.println("🌐 API Base URL: http://localhost:8080/api");
        System.out.println("⚡ Microservicios activos:");
        System.out.println("   - Control de Usuarios (/api/usuarios)");
        System.out.println("   - Autenticación (/api/auth)");
        System.out.println("   - Resultados de Análisis (/api/resultados)");
        System.out.println("📊 Base de datos: Oracle");
        System.out.println("🔐 Seguridad: JWT + BCrypt");
        System.out.println("==============================================");
    }
}

