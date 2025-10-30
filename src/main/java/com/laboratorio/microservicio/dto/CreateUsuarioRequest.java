package com.laboratorio.microservicio.dto;

import com.laboratorio.microservicio.entity.Usuario;
import jakarta.validation.constraints.*;

/**
 * DTO para crear un nuevo usuario
 */
public class CreateUsuarioRequest {

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @NotNull(message = "El rol es obligatorio")
    private Usuario.RolUsuario rol;

    // Constructor por defecto
    public CreateUsuarioRequest() {
    }

    // Constructor con parámetros
    public CreateUsuarioRequest(String nombreCompleto, String email, String contrasena, Usuario.RolUsuario rol) {
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y Setters
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

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

    public Usuario.RolUsuario getRol() {
        return rol;
    }

    public void setRol(Usuario.RolUsuario rol) {
        this.rol = rol;
    }
}

