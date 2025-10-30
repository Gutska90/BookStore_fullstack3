package com.laboratorio.microservicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un usuario no fue encontrado
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public static UsuarioNotFoundException withId(Long id) {
        return new UsuarioNotFoundException("Usuario no encontrado con ID: " + id);
    }

    public static UsuarioNotFoundException withEmail(String email) {
        return new UsuarioNotFoundException("Usuario no encontrado con email: " + email);
    }
}

