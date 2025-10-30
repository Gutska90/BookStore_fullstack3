package com.laboratorio.microservicio.dto;

/**
 * DTO para respuesta de login
 */
public class LoginResponse {

    private String token;
    private UsuarioResponse usuario;
    private String mensaje;

    // Constructor por defecto
    public LoginResponse() {
    }

    // Constructor con parámetros
    public LoginResponse(String token, UsuarioResponse usuario, String mensaje) {
        this.token = token;
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

