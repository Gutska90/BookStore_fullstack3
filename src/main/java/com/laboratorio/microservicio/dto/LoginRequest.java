package com.laboratorio.microservicio.dto;

import jakarta.validation.constraints.*;

/**
 * DTO para solicitud de login
 */
public class LoginRequest {

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    // Constructor por defecto
    public LoginRequest() {
    }

    // Constructor con parámetros
    public LoginRequest(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

