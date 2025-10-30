package com.laboratorio.microservicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un laboratorio no fue encontrado
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class LaboratorioNotFoundException extends RuntimeException {

    public LaboratorioNotFoundException(String message) {
        super(message);
    }

    public static LaboratorioNotFoundException withId(Long id) {
        return new LaboratorioNotFoundException("Laboratorio no encontrado con ID: " + id);
    }
}

