package com.laboratorios.usuarios.exception;

public class UsuarioNotFoundException extends RuntimeException {
    
    public UsuarioNotFoundException(String message) {
        super(message);
    }
    
    public static UsuarioNotFoundException withId(Long id) {
        return new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
    }
}

