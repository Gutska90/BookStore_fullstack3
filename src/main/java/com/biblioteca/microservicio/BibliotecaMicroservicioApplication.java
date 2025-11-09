package com.biblioteca.microservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal de la aplicación Spring Boot optimizada
 * Microservicio para gestionar libros en una biblioteca
 * 
 * Optimizaciones incluidas:
 * - Caché habilitado para mejor performance
 * - Auditoría JPA para timestamps automáticos
 * - Configuración optimizada para producción
 */
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class BibliotecaMicroservicioApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BibliotecaMicroservicioApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🚀 Microservicio Biblioteca iniciado correctamente");
        System.out.println("📡 Puerto: 8080");
        System.out.println("🌐 API Base URL: http://localhost:8080/api/libros");
        System.out.println("⚡ Optimizaciones: Caché, Paginación, DTOs, Validaciones");
        System.out.println("📊 Nuevos endpoints:");
        System.out.println("   - GET /api/libros (con paginación)");
        System.out.println("   - GET /api/libros/buscar?termino=...");
        System.out.println("   - GET /api/libros/rango-anios?inicio=...&fin=...");
        System.out.println("   - GET /api/libros/autores");
        System.out.println("   - GET /api/libros/generos");
        System.out.println("==============================================");
    }
}

