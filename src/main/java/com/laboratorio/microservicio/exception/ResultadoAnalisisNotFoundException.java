package com.laboratorio.microservicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción personalizada para indicar que un resultado de análisis no fue encontrado
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResultadoAnalisisNotFoundException extends RuntimeException {

    public ResultadoAnalisisNotFoundException(String message) {
        super(message);
    }

    public static ResultadoAnalisisNotFoundException withId(Long id) {
        return new ResultadoAnalisisNotFoundException("Resultado de análisis no encontrado con ID: " + id);
    }
}

