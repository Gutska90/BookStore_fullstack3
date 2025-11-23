package com.laboratorios.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(UsuariosApplication.class, args);
        System.out.println("==============================================");
        System.out.println("🚀 Microservicio Usuarios iniciado correctamente");
        System.out.println("📡 Puerto: 8081");
        System.out.println("🌐 API Base URL: http://localhost:8081/api/usuarios");
        System.out.println("==============================================");
    }
}

