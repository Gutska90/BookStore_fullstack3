package com.laboratorios.resultados.dto;

import java.time.LocalDateTime;

public class ResultadoResponse {
    
    private Long id;
    private Long pacienteId;
    private Long laboratorioId;
    private String tipoAnalisis;
    private String resultado;
    private String observaciones;
    private LocalDateTime fechaAnalisis;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    
    public ResultadoResponse() {
    }
    
    public ResultadoResponse(Long id, Long pacienteId, Long laboratorioId, String tipoAnalisis,
                            String resultado, String observaciones, LocalDateTime fechaAnalisis,
                            LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.laboratorioId = laboratorioId;
        this.tipoAnalisis = tipoAnalisis;
        this.resultado = resultado;
        this.observaciones = observaciones;
        this.fechaAnalisis = fechaAnalisis;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
    
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}

