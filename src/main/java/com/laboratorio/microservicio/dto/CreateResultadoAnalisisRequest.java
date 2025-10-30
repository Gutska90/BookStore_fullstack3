package com.laboratorio.microservicio.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 * DTO para crear un nuevo resultado de análisis
 */
public class CreateResultadoAnalisisRequest {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID del laboratorio es obligatorio")
    private Long laboratorioId;

    @NotBlank(message = "El tipo de análisis es obligatorio")
    @Size(min = 3, max = 200, message = "El tipo de análisis debe tener entre 3 y 200 caracteres")
    private String tipoAnalisis;

    @NotNull(message = "La fecha de realización es obligatoria")
    private LocalDate fechaRealizacion;

    private LocalDate fechaResultado;

    private String valores;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;

    private String estado;

    // Constructor por defecto
    public CreateResultadoAnalisisRequest() {
    }

    // Getters y Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

