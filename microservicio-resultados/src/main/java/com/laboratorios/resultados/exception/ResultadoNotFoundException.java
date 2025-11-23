package com.laboratorios.resultados.exception;

public class ResultadoNotFoundException extends RuntimeException {
    
    public ResultadoNotFoundException(String message) {
        super(message);
    }
    
    public static ResultadoNotFoundException withId(Long id) {
        return new ResultadoNotFoundException("Resultado no encontrado con ID: " + id);
    }
}

