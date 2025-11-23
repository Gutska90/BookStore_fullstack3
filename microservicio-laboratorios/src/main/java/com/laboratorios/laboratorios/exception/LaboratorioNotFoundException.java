package com.laboratorios.laboratorios.exception;

public class LaboratorioNotFoundException extends RuntimeException {
    
    public LaboratorioNotFoundException(String message) {
        super(message);
    }
    
    public static LaboratorioNotFoundException withId(Long id) {
        return new LaboratorioNotFoundException("Laboratorio no encontrado con ID: " + id);
    }
}

