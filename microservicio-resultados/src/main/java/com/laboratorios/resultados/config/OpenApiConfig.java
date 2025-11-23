package com.laboratorios.resultados.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microservicio de Resultados API")
                        .version("1.0.0")
                        .description("API REST para la gestión de resultados de análisis del sistema de laboratorios")
                        .contact(new Contact()
                                .name("Sistema de Laboratorios")
                                .email("admin@laboratorios.com")));
    }
}

