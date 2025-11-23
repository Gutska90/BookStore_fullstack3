package com.laboratorios.resultados.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class ResultadoRequest {
    
    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;
    
    @NotNull(message = "El ID del laboratorio es obligatorio")
    private Long laboratorioId;
    
    @NotBlank(message = "El tipo de análisis es obligatorio")
    @Size(min = 3, max = 100, message = "El tipo de análisis debe tener entre 3 y 100 caracteres")
    private String tipoAnalisis;
    
    @NotBlank(message = "El resultado es obligatorio")
    private String resultado;
    
    private String observaciones;
    
    private LocalDateTime fechaAnalisis;
    
    // Getters y Setters
    public Long getPacienteId() {
        return pacienteId;
    }
    
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
    
    public Long getLaboratorioId() {
        return laboratorioId;
    }
    
    public void setLaboratorioId(Long laboratorioId) {
        this.laboratorioId = laboratorioId;
    }
    
    public String getTipoAnalisis() {
        return tipoAnalisis;
    }
    
    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }
    
    public String getResultado() {
        return resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public LocalDateTime getFechaAnalisis() {
        return fechaAnalisis;
    }
    
    public void setFechaAnalisis(LocalDateTime fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
    }
}

